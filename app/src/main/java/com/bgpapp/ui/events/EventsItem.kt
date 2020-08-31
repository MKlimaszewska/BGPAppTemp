package com.bgpapp.ui.events

import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator

data class EventsItem(
    val title: String,
    val place: String,
    val date: String,
    val time: String,
    val localization: String,
    val photoUrl: String,
    val navigationCommand: NavigationCommand
): Navigator by DefaultNavigator() {

    fun onClick() {
        navigate(navigationCommand)
    }

}