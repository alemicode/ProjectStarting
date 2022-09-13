package app.lahzebar.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.lahzebar.MainViewModelFactory
import app.lahzebar.activities.main.MainViewModel
import app.lahzebar.di.FragmentScope
import app.lahzebar.di.ViewModelKey
import app.lahzebar.features.home.HomeFragment
import app.lahzebar.features.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    fun viewModelFactory(viewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}

@Module
internal interface FragmentBuilder {

    @ContributesAndroidInjector
    @FragmentScope
    fun homeFragment(): HomeFragment
}
