package app.lahzebar.commons.navigation

interface Navigator {
    fun navigateTo(destinationEvent: DestinationEvent)
    fun getDestinationId(destination: Destination): Int
    fun clearBackstack(destination: Destination): Boolean
}
