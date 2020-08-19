package com.bgpapp.navigation

import androidx.navigation.NavDirections
import com.bgpapp.common.DataEventEmitter
import com.bgpapp.common.emit

interface Navigator {

    val navigationCommands: DataEventEmitter<NavigationCommand>

    fun navigate(command: NavigationCommand) = navigationCommands.emit(command)
    fun navigate(directions: NavDirections) = navigationCommands.emit(NavigationCommand.To(directions))
}