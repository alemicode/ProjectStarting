package app.lahzebar

import android.app.Application
import app.lahzebar.data.di.DaggerDataComponent
import app.lahzebar.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class LahzebarApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .applicationContext(this)
            .dataComponent(DaggerDataComponent.builder().context(applicationContext).build())
            .build().inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
