package core.views.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.BuildConfig
import androidx.viewbinding.ViewBinding
import core.views.util.OnBackPressListener
import core.views.util.instancestate.InstanceStateHandler
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

abstract class BaseFragment
<T : ViewBinding, S : BaseState, A : BaseAction, K : BaseViewModel<S, A, out BaseMutation>>(
    private val clazz: Class<K>
) : Fragment(), HasAndroidInjector, OnBackPressListener, InstanceStateHandler {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[clazz]
    }

    private var _binding: T? = null
    protected open var binding: T
        get() = _binding ?: throw IllegalAccessException("")
        set(value) {
            _binding = value
        }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoreBundle(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initialView(viewModel::tryEmitAction)
        lifecycleScope.launchWhenStarted {
            viewModel.state.onEach { render(it, viewModel::tryEmitAction) }.collect()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Log.d("BaseFragment", clazz.name)
            findNavController().backQueue.forEach {
                if (BuildConfig.DEBUG)
                    Log.d("BaseFragment", "queue: ${it.destination.label}")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        saveBundle(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onBackPressed(): Boolean {
        val childFragment = (childFragmentManager.primaryNavigationFragment as? NavHostFragment)
            ?.childFragmentManager?.primaryNavigationFragment
            ?: childFragmentManager.primaryNavigationFragment
        return (childFragment as? OnBackPressListener)?.onBackPressed() ?: false
    }

    protected fun hideKeyboard(view: View) {
        view.clearFocus()
        val input: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        input.hideSoftInputFromWindow(view.windowToken, 0)
    }

    abstract fun initialView(emitAction: (A) -> Boolean)
    abstract fun render(state: S, emitAction: (A) -> Boolean)
}
