package com.bgpapp.navigation

import androidx.navigation.NavDirections
import java.net.URI

sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()
    data class ToUri(val uri: URI) : NavigationCommand()
    data class ToFlow<T : Any>(val destination: Class<T>, val clearStack: Boolean) : NavigationCommand() {
        constructor(destination: Class<T>) : this(destination, clearStack = false)
    }
    object FinishFlow : NavigationCommand()
    object Back : NavigationCommand()
    data class BackTo(val destinationId: Int) : NavigationCommand()
    object ToRoot : NavigationCommand()
}