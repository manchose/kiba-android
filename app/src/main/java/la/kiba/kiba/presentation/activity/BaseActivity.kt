package la.kiba.kiba.presentation.activity

import android.support.v7.app.AppCompatActivity
import la.kiba.kiba.KibaApplication
import la.kiba.kiba.presentation.di.component.ActivityComponent
import la.kiba.kiba.presentation.di.module.ActivityModule
import la.kiba.kiba.presentation.viewmodel.ViewModel

/**
 * Created by sasaki_nobuya on 2017/04/23.
 */
open class BaseActivity : AppCompatActivity() {
    fun getInjector(): ActivityComponent {
        val kibaApp = application as KibaApplication
        return kibaApp.injector.activityComponent(ActivityModule(this))
    }

    protected fun bindViewModel(viewModel: ViewModel) {
        // TODO: 実装すること
    }
}
