package la.kiba.kiba.presentation.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.github.gfx.android.orma.DatabaseHandle
import dagger.Module
import dagger.Provides
import la.kiba.kiba.infla.entity.OrmaDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by sasaki_nobuya on 2017/04/23.
 *
 * アプリケーション内でシングルトン的に使うインスタンスを提供するモジュール
 * 複数のmoduleにまたがって利用されるインスタンスはここの@Providesで提供する
 */
@Module
class AppModule(val application: Application) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        // FIXME: sasaki インターセプターなど設定すること(blogにまとめる)
        val builder = OkHttpClient.Builder()
        //ログ出力設定
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        // タイムアウト設定
        builder.connectTimeout(30L, TimeUnit.SECONDS)
        return builder.build()
    }

    /** baseUrlは利用側で動的に設定する */
    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
    }

    /** dagger2の問題でOrmaDatabaseをinjectするとビルドできないため、利用側でOrmaDatabaseにcastする*/
    @Singleton
    @Provides
    fun provideOrmaDatabase(context: Context): DatabaseHandle {
        // FIXME: sasaki 適切な設定を行うこと(blogにまとめる)
        return OrmaDatabase.builder(context).build()
    }

    /** SharedPreferenceのラッパークラスを返却するようにすること */
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("kiba-preference", Context.MODE_PRIVATE)
    }
}

