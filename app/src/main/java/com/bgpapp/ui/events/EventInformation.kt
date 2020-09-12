package com.bgpapp.ui.events

import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import java.io.Serializable

data class EventInformation (
    val eventItem: EventsItem,
    val navigationCommand: NavigationCommand
): Navigator by DefaultNavigator(), Serializable {
    fun onClick() {
        navigate(navigationCommand)
    }
}