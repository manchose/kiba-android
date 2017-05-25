package la.kiba.kiba.presentation.fragment

import android.app.Fragment
import la.kiba.kiba.KibaApplication
import la.kiba.kiba.presentation.di.component.AppComponent

/**
 * Created by sasaki_nobuya on 2017/04/25.
 */
open class BaseFragment : Fragment() {
    fun getInjector(): AppComponent {
        val kibaApp = activity.application as KibaApplication
        return kibaApp.injector
    }
}
