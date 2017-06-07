package la.kiba.kiba.domain.usecase

import io.reactivex.Observable
import io.reactivex.Single
import la.kiba.kiba.infla.entity.App
import la.kiba.kiba.infla.entity.OAuthToken
import la.kiba.kiba.infla.repository.AppRepository
import la.kiba.kiba.infla.repository.OAuthRepository
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
class SignInUseCase @Inject constructor(val appRepository: AppRepository, val oAuthRepository: OAuthRepository) {
    fun login(instance: String, email: String, password: String): Observable<OAuthToken> {
        return appRepository.get(instance).flatMap { app -> auth(app, email, password) }
    }

    private fun auth(app: App, email: String, password: String): Observable<OAuthToken> {
        return oAuthRepository.login(email, password, app)
    }

    fun isLogin(): Single<Boolean> {
        return oAuthRepository.isLogin()
    }
}
