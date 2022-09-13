package core.views.base

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import core.views.util.OnBackPressListener
import core.views.util.instancestate.InstanceStateHandler
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity
<T : ViewBinding, S : BaseState, A : BaseAction, K : BaseViewModel<S, A, out BaseMutation>>(
    private val clazz: Class<K>
) : AppCompatActivity(), HasAndroidInjector, InstanceStateHandler {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[clazz]
    }

    private var _binding: T? = null
    protected var binding: T
        get() = _binding ?: throw IllegalAccessException("")
        set(value) {
            _binding = value
        }

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreBundle(savedInstanceState)
        AndroidInjection.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveBundle(outState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    fun isAllPermissionsGranted(permissions: Array<out String>, grantResults: IntArray): Boolean {
        if (permissions.isEmpty()) return true
        if (grantResults.isEmpty() || grantResults.size != permissions.size) return false
        grantResults.forEach { if (it != PackageManager.PERMISSION_GRANTED) return false }
        return true
    }

    fun isAllPermissionsGranted(permissions: Array<out String>): Boolean {
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(applicationContext, it)
                != PackageManager.PERMISSION_GRANTED
            ) return false
        }
        return true
    }

    override fun onBackPressed() {
        val fragment = (supportFragmentManager.primaryNavigationFragment as? NavHostFragment)
            ?.childFragmentManager?.primaryNavigationFragment
            ?: supportFragmentManager.primaryNavigationFragment
        if ((fragment as? OnBackPressListener)?.onBackPressed() != true)
            super.onBackPressed()
    }

    protected fun hideKeyboard(view: View) {
        view.clearFocus()
        val input: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        input.hideSoftInputFromWindow(view.windowToken, 0)
    }

    abstract fun initialView(emitAction: (A) -> Boolean)
    abstract fun render(state: S, emitAction: (A) -> Boolean)
}
