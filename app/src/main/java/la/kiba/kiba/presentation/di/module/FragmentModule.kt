package la.kiba.kiba.presentation.di.module

import dagger.Module
import dagger.Provides
import la.kiba.kiba.presentation.fragment.BaseFragment

/**
 * Created by sasaki_nobuya on 2017/04/25.
 */
@Module
class FragmentModule(val fragment: BaseFragment) {
    @Provides
    fun provideFragment(): BaseFragment {
        return fragment
    }
}
