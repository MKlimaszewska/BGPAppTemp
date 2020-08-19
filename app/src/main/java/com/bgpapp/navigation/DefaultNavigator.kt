package com.bgpapp.navigation

import com.bgpapp.common.DataEventEmitter

class DefaultNavigator : Navigator {
    override val navigationCommands = DataEventEmitter<NavigationCommand>()
}