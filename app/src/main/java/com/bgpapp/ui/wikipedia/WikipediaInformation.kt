package com.bgpapp.ui.wikipedia

import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator

data class WikipediaInformation (
    val wikipediaItem: WikipediaItem,
    val navigationCommand: NavigationCommand
): Navigator by DefaultNavigator() {
    fun onClick() {
        navigate(navigationCommand)
    }
}