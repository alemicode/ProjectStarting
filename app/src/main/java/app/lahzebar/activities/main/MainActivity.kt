package app.lahzebar.activities.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import app.lahzebar.R
import app.lahzebar.commons.navigation.Destination
import app.lahzebar.commons.navigation.DestinationEvent
import app.lahzebar.commons.navigation.Navigator
import app.lahzebar.databinding.ActivityMainBinding
import core.views.base.BaseActivity

class MainActivity :
    BaseActivity<ActivityMainBinding, MainState, MainAction, MainViewModel>(
        MainViewModel::class.java
    ),
    Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initialView(emitAction: (MainAction) -> Boolean) {
        // STOPSHIP
    }

    override fun render(state: MainState, emitAction: (MainAction) -> Boolean) {
        // STOPSHIP
    }

    override fun navigateTo(destinationEvent: DestinationEvent) {
        getNavController()?.navigate(destinationEvent)
    }

    override fun getDestinationId(destination: Destination): Int = when (destination) {
        Destination.HOME -> app.lahzebar.features.home.R.id.fragment_home
    }

    override fun clearBackstack(destination: Destination): Boolean =
        getNavController()?.clearBackStack(getDestinationId(destination)) ?: false

    private fun getNavController() =
        (supportFragmentManager.findFragmentById(binding.mainNavContainer.id) as? NavHostFragment)
            ?.navController
}
