package la.kiba.kiba.infla.repository

import com.github.gfx.android.orma.DatabaseHandle
import io.reactivex.Observable
import la.kiba.kiba.infla.entity.OAuthToken
import la.kiba.kiba.infla.entity.OrmaDatabase
import la.kiba.kiba.infla.net.OAuthApiClient
import la.kiba.kiba.lib.MastodonInstance
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * Created by sasaki_nobuya on 2017/05/04.
 *
 * domainはinterfaceにしか依存せず、API/Cacheなどのインフラに依存しない
 * こうすることでRepository層を切り替えられるようにする
 * Repository層は非同期で行う前提のため、呼び出しもとで別スレッドをたてて実行すること
 * TODO: client_secretとclient_idとかはOAuthTokenとは違う。OAuthの仕組みを理解して命名し直す
 **/
class OAuthRepository @Inject constructor(val retrofitBuilder: Retrofit.Builder, databaseHandle: DatabaseHandle) {
    var ormaDatabase: OrmaDatabase = databaseHandle as OrmaDatabase

    /**
     * OAuthTokenを取得する。すでに取得済みの場合はSQLiteに保存済みのOAuthTokenを返却する
     * @param instance mastodonインスタンス
     */
    fun get(instance: String): Observable<OAuthToken> {
        /*
         * Repository層では、remoteから取得できたらその値をDBに保存して、その上でObservable(Single)を返却する必要がある。
         * これをなにも考えずにやると、subscribeをRepository層で使うことになるため、メソッドを呼ぶ時点で呼び元のスレッドでAPI通信が走ってしまうことになる。
         * かといって、blockingGetで同期的に処理を行ってもその時点でAPI通信が走ってしまうし、try catchを書く必要がある。
         * これを解決するためには、flatMapを使う
         * TODO: ここをrepositoryライブラリみたいにきりだせないかな？
         */
        val validatedInstance = MastodonInstance(instance).validatedInstance()
        return getOAuthTokenFromLocal(validatedInstance).concatWith(getOAuthTokenFromRemote(validatedInstance))
//        return getOAuthTokenFromLocal(validatedInstance).switchIfEmpty {
//            getOAuthTokenFromRemote(validatedInstance)
//        }.flatMap { token ->
//            ormaDatabase.insertIntoOAuthToken(token)
//            Observable.create<OAuthToken> { emitter ->
//                emitter?.onNext(token)
//                emitter?.onComplete()
//            }
//        }
//        return getOAuthTokenFromLocal(validatedInstance).flatMap { token ->
//            if (token == null) {
//                getOAuthTokenFromRemote(validatedInstance)
//            } else {
//                Observable.create<OAuthToken> { emitter ->
//                    emitter?.onNext(token)
//                    emitter?.onComplete()
//                }
//            }
//        }.flatMap { token ->
//            ormaDatabase.insertIntoOAuthToken(token)
//            Observable.create<OAuthToken> { emitter ->
//                emitter?.onNext(token)
//                emitter?.onComplete()
//            }
//        }
    }

    private fun getOAuthTokenFromRemote(instance: String): Observable<OAuthToken> {
        // TODO: URLの処理をもう少しきれいにすること
        val retrofit = retrofitBuilder.baseUrl("https://$instance/").build()
        val service = retrofit.create(OAuthApiClient::class.java)
        return service.register()
    }

    private fun getOAuthTokenFromLocal(instance: String): Observable<OAuthToken> {
        return ormaDatabase.selectFromOAuthToken()
                .instanceEq(instance).executeAsObservable()
    }
}