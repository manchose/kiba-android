package la.kiba.kiba.infla.repository

import com.github.gfx.android.orma.DatabaseHandle
import io.reactivex.Observable
import io.reactivex.Single
import la.kiba.kiba.infla.entity.App
import la.kiba.kiba.infla.entity.OAuthToken
import la.kiba.kiba.infla.entity.OrmaDatabase
import la.kiba.kiba.infla.net.OAuthTokenApiClient
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */

class OAuthRepository @Inject constructor(val retrofitBuilder: Retrofit.Builder, databaseHandle: DatabaseHandle) {
    var ormaDatabase: OrmaDatabase = databaseHandle as OrmaDatabase

    fun isLogin(): Single<Boolean> {
        return Single.create<Boolean> { ormaDatabase.selectFromAccount().count() > 0 }
    }

    fun login(email: String, password: String, app: App): Observable<OAuthToken> {
        return Observable.create<OAuthToken> { emitter ->
            var token = getOAuthTokenFromLocal(email, app)
            if (token == null) {
                token = getOAuthTokenFromRemote(email, password, app)
            }
            if (token == null) {
                // TODO: ここのErrorクラスは独自クラスにする
                emitter.onError(null)
            } else {
                ormaDatabase.insertIntoOAuthToken(token)
            }
            emitter.onNext(token)
            emitter.onComplete()
        }
    }


    private fun getOAuthTokenFromLocal(email: String, app: App): OAuthToken? {
        try {
            return ormaDatabase.selectFromOAuthToken().instanceEq(app.instance).emailEq(email).executeAsObservable().blockingFirst()
        } catch (e: NoSuchElementException) {
            return null
        }
    }

    private fun getOAuthTokenFromRemote(email: String, password: String, app: App): OAuthToken? {
        val retrofit = retrofitBuilder.baseUrl("https://${app.instance}/").build()
        val service = retrofit.create(OAuthTokenApiClient::class.java)
        try {
            return service.issue(app.clientId, app.clientSecret, email, password).blockingFirst()
        } catch (e: NoSuchElementException) {
            return null
        }
    }
}
