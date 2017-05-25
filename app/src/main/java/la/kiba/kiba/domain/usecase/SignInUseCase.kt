package la.kiba.kiba.domain.usecase

import android.util.Log
import io.reactivex.Single
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import la.kiba.kiba.infla.entity.OAuthToken
import la.kiba.kiba.infla.repository.AccountRepository
import la.kiba.kiba.infla.repository.OAuthRepository
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
class SignInUseCase @Inject constructor(val oAuthRepository: OAuthRepository, val accountRepository: AccountRepository) {
    fun login(instance: String) {
        oAuthRepository.get(instance).subscribeOn(Schedulers.io()).subscribe(object : DisposableObserver<OAuthToken>() {
            override fun onComplete() {
            }

            override fun onNext(token: OAuthToken) {
                auth(token)
            }

            override fun onError(e: Throwable?) {
                Log.i("kiba-log", "onError")
            }
        })
    }

    private fun auth(token: OAuthToken) {
        accountRepository
    }

    fun isLogin(): Single<Boolean> {
        return accountRepository.isLogin()
    }
}
