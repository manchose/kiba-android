package la.kiba.kiba.presentation.di.component

import dagger.Subcomponent
import la.kiba.kiba.presentation.di.module.FragmentModule
import la.kiba.kiba.presentation.fragment.NonLoginTopFragment
import la.kiba.kiba.presentation.fragment.TimelineFragment

/**
 * Created by sasaki_nobuya on 2017/04/25.
 */
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun inject(fragment: TimelineFragment)
    fun inject(fragment: NonLoginTopFragment)
}