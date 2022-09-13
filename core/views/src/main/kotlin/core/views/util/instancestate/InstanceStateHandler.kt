package core.views.util.instancestate

import android.os.Bundle

interface InstanceStateHandler {

    private val savable: Bundle
        get() = Bundle()

    fun getBundle() = savable
    fun saveBundle(outState: Bundle) = outState.putBundle("_state", savable)
    fun restoreBundle(savedInstanceState: Bundle?) = savedInstanceState?.let {
        savable.putAll(it.getBundle("_state"))
    }
}
