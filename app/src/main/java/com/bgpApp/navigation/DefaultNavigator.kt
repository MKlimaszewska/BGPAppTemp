package com.bgpApp.navigation

import com.bgpApp.common.DataEventEmitter

class DefaultNavigator : Navigator {
    override val navigationCommands = DataEventEmitter<NavigationCommand>()
}