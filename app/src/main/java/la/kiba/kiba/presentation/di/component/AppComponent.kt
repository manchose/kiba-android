package la.kiba.kiba.presentation.di.component

import dagger.Component
import la.kiba.kiba.presentation.di.module.ActivityModule
import la.kiba.kiba.presentation.di.module.AppModule
import la.kiba.kiba.presentation.di.module.FragmentModule
import javax.inject.Singleton

/**
 * Created by sasaki_nobuya on 2017/04/23.
 *
 * このComponentはApplicationオブジェクトに保持させて、アプリの起動から終了までのライフサイクルで生存し続ける様にする
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    // 親は子Componentを生成するabstractなfactoryメソッドを提供する必要がある。
    // こうすることで、子Componentは親ComponentのScopeが管理する依存オブジェクトを利用できる(AppModuleで提供されるオブジェクト)
    // 子Componentは実際は親Componentの非staticな内部クラスとして宣言されるため、親Componentよりライフサイクルは短くなる
    fun activityComponent(activityModule: ActivityModule): ActivityComponent

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent
}
