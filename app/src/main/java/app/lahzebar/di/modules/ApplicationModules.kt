package app.lahzebar.di.modules

import app.lahzebar.activities.main.MainActivity
import app.lahzebar.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal class ApplicationModule

@Module
internal interface ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [FragmentBuilder::class, ViewModelModule::class]
    )
    @ActivityScope
    fun mainActivity(): MainActivity
}

@Module
internal interface ServiceBuilder
