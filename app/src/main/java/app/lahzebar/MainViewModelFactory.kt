package app.lahzebar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.lahzebar.di.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class MainViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: throw IllegalArgumentException("unknown model class $modelClass")
        return modelClass.cast(creator.get())!!
    }
}
