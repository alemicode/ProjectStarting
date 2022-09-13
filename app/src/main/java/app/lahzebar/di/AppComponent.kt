package app.lahzebar.di

import android.content.Context
import app.lahzebar.LahzebarApplication
import app.lahzebar.data.di.DataComponent
import app.lahzebar.di.modules.ActivityBuilder
import app.lahzebar.di.modules.ApplicationModule
import app.lahzebar.di.modules.ServiceBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@ApplicationScope
@Component(
    dependencies = [DataComponent::class],
    modules = [
        AndroidInjectionModule::class, ApplicationModule::class,
        ActivityBuilder::class, ServiceBuilder::class
    ]
)
interface AppComponent {
    fun inject(application: LahzebarApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(@ApplicationContext context: Context): Builder
        fun dataComponent(dataComponent: DataComponent): Builder
        fun build(): AppComponent
    }
}
