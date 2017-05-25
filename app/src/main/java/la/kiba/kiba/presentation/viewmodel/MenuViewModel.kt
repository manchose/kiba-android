package la.kiba.kiba.presentation.viewmodel

import android.view.View
import la.kiba.kiba.presentation.activity.LoginActivity

/**
 * Created by sasaki_nobuya on 2017/05/07.
 */
class MenuViewModel(val title: String) : ViewModel() {
    fun menuClick(view: View) {
        view.context.startActivity(LoginActivity.createIntent(view.context))
    }
}
