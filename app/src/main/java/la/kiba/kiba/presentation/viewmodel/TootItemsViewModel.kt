package la.kiba.kiba.presentation.viewmodel

import la.kiba.kiba.infla.entity.Toot
import javax.inject.Inject

/**
 * Created by sasaki_nobuya on 2017/04/25.
 */
class TootItemsViewModel @Inject constructor() {
    lateinit private var tootItemViewModels: List<TootItemViewModel>

    fun setToots(toots: List<Toot>) {
        tootItemViewModels = toots.map(::TootItemViewModel)
    }

    fun getViewModel(position: Int): TootItemViewModel {
        return tootItemViewModels[position]
    }

    fun viewModelSize(): Int {
        return tootItemViewModels.size
    }
}
