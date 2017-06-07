package la.kiba.kiba.presentation.viewmodel

import android.view.View
import la.kiba.kiba.presentation.activity.LoginActivity

/**
 * Created by sasaki_nobuya on 2017/05/07.
 */
class MenuViewModel(val drawerMenu: MenuListViewModel.DrawerMenu) : ViewModel() {
    fun menuClick(view: View) {
        val selectedMenu = MenuListViewModel.DrawerMenu.fromId(view.tag as Int)
        when (selectedMenu) {
            MenuListViewModel.DrawerMenu.ADD_ACCOUNT -> view.context.startActivity(LoginActivity.createIntent(view.context))
        }
    }
}
