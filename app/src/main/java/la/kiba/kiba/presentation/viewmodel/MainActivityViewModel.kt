package la.kiba.kiba.presentation.viewmodel

import la.kiba.kiba.domain.usecase.SignInUseCase
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/04/23.
 */

class MainActivityViewModel @Inject constructor(val signInUseCase: SignInUseCase) {
    fun isLogin(): Boolean {
        return false//signInUseCase.isLogin().subscribe()
    }
}