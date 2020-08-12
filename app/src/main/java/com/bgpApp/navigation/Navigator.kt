package com.bgpApp.navigation

import androidx.navigation.NavDirections
import com.bgpApp.common.DataEventEmitter
import com.bgpApp.common.emit

interface Navigator {

    val navigationCommands: DataEventEmitter<NavigationCommand>

    fun navigate(command: NavigationCommand) = navigationCommands.emit(command)
    fun navigate(directions: NavDirections) = navigationCommands.emit(NavigationCommand.To(directions))
}