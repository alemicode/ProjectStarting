package app.lahzebar.commons.navigation

import androidx.navigation.NavOptions

sealed class DestinationEvent(val navOptions: NavOptions?) {
    class Home(navOptions: NavOptions? = null) : DestinationEvent(navOptions)
}
