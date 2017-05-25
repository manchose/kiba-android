package la.kiba.kiba.presentation.di.module

import dagger.Module
import dagger.Provides
import la.kiba.kiba.presentation.activity.BaseActivity

/**
 * Created by sasaki_nobuya on 2017/04/23.
 */
@Module
class ActivityModule(val baseActivity: BaseActivity) {
    @Provides
    fun provideActivity(): BaseActivity {
        return baseActivity
    }
}