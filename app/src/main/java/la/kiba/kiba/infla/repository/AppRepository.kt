package la.kiba.kiba.infla.repository

import com.github.gfx.android.orma.DatabaseHandle
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import la.kiba.kiba.infla.entity.App
import la.kiba.kiba.infla.entity.OrmaDatabase
import la.kiba.kiba.infla.net.AppApiClient
import la.kiba.kiba.lib.MastodonInstance
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject


/**
 * Created by sasaki_nobuya on 2017/05/04.
 *
 * domainはinterfaceにしか依存せず、API/Cacheなどのインフラに依存しない
 * こうすることでRepository層を切り替えられるようにする
 * Repository層は非同期で行う前提のため、呼び出しもとで別スレッドをたてて実行すること
 * TODO: client_secretとclient_idとかはOAuthTokenとは違う。OAuthの仕組みを理解して命名し直す
 **/
class AppRepository @Inject constructor(val retrofitBuilder: Retrofit.Builder, databaseHandle: DatabaseHandle) {
    var ormaDatabase: OrmaDatabase = databaseHandle as OrmaDatabase

    /**
     * OAuthTokenを取得する。すでに取得済みの場合はSQLiteに保存済みのOAuthTokenを返却する
     * @param instance mastodonインスタンス
     */
    fun get(instance: String): Observable<App> {
        /*
         * flatMapを使ってうまくやれるのかもしれないが、やり方がわからないのでsubscribeのネストでごまかす
         * TODO: ここをrepositoryライブラリみたいにきりだせないかな...
         */
        val validatedInstance = MastodonInstance(instance).validatedInstance()
        return Observable.create<App> { emitter ->
            getAppFromLocal(validatedInstance).subscribe(object : DisposableObserver<App>() {
                override fun onError(e: Throwable?) {
                    try {
                        val token = getAppFromRemote(validatedInstance).blockingFirst()
                        token.instance = validatedInstance
                        ormaDatabase.insertIntoApp(token)
                        emitter.onNext(token)
                        emitter.onComplete()
                    } catch (e: NoSuchElementException) {
                        emitter.onError(e)
                    }
                }

                override fun onComplete() {
                    emitter.onComplete()
                }

                override fun onNext(t: App) {
                    emitter.onNext(t)
                }
            })
        }
    }

    fun getAppFromRemote(instance: String): Observable<App> {
        // TODO: URLの処理をもう少しきれいにすること
        val retrofit = retrofitBuilder.baseUrl("https://$instance/").build()
        val service = retrofit.create(AppApiClient::class.java)
        return service.register()
    }

    // TODO: ここをblockingFirst()にしちゃう。基本的にpublicメソッド以外はblockingでいいと思う
    fun getAppFromLocal(instance: String): Observable<App> {
        return Observable.create<App> { emitter ->
            try {
                var token = ormaDatabase.selectFromApp()
                        .instanceEq(instance).executeAsObservable().blockingFirst()
                emitter.onNext(token)
                emitter.onComplete()
            } catch (e: NoSuchElementException) {
                emitter.onError(e)
            }
        }
    }
}