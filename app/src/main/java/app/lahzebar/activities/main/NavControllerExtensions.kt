package app.lahzebar.activities.main

import androidx.navigation.NavController
import app.lahzebar.NavGraphDirections
import app.lahzebar.commons.navigation.DestinationEvent

fun NavController.navigate(destinationEvent: DestinationEvent) =
    when (destinationEvent) {
        is DestinationEvent.Home -> navigate(
            NavGraphDirections.actionGlobalHomeNavGraph(),
            destinationEvent.navOptions
        )
    }
