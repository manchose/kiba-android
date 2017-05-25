package la.kiba.kiba.presentation.di.component

import dagger.Subcomponent
import la.kiba.kiba.presentation.activity.LoginActivity
import la.kiba.kiba.presentation.activity.MainActivity
import la.kiba.kiba.presentation.di.module.ActivityModule

/**
 * Created by sasaki_nobuya on 2017/04/23.
 * Componentでmodules = xxxで定義したmoduleとmoduleを利用したいオブジェクトを関連付ける
 */
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)

    fun inject(activity: LoginActivity)
}