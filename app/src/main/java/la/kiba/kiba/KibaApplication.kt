package la.kiba.kiba

import android.app.Application
import la.kiba.kiba.presentation.di.component.AppComponent
import la.kiba.kiba.presentation.di.component.DaggerAppComponent
import la.kiba.kiba.presentation.di.module.AppModule

/**
 * Created by sasaki_nobuya on 2017/04/23.
 */
class KibaApplication : Application() {

    lateinit var injector: AppComponent

    override fun onCreate() {
        super.onCreate()

        injector = buildAppComponent()
    }

    protected fun buildAppComponent(): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
