package la.kiba.kiba.presentation.viewmodel

import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
class MenuListViewModel @Inject constructor() : ViewModel() {

    companion object {
        val MENU_ITEMS = arrayListOf("アカウント追加", "カラム設定")
    }

    private var menuViewModels: List<MenuViewModel>

    init {
        menuViewModels = MENU_ITEMS.map(::MenuViewModel)
    }

    fun getViewModel(position: Int): MenuViewModel {
        return menuViewModels[position]
    }

    fun viewModelSize(): Int {
        return menuViewModels.size
    }
}
