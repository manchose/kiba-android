package la.kiba.kiba.presentation.viewmodel

import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/05/06.
 */
class MenuListViewModel @Inject constructor() : ViewModel() {

    enum class DrawerMenu(val title: String, val id: Int) {
        ADD_ACCOUNT("アカウント追加", 1),
        ACCOUNT_SETTING("アカウント設定", 2),
        COLUMN_SETTING("カラム設定", 3);

        companion object {
            fun fromId(id: Int): DrawerMenu? {
                return values().find {
                    it.id == id
                }
            }
        }
    }

    private var menuViewModels: List<MenuViewModel>

    init {
        menuViewModels = DrawerMenu.values().map { MenuViewModel(it) }
    }

    fun getViewModel(position: Int): MenuViewModel {
        return menuViewModels[position]
    }

    fun viewModelSize(): Int {
        return menuViewModels.size
    }
}
