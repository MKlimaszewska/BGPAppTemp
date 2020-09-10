package com.bgpapp.ui.wikipedia

import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator

data class WikipediaItem(
    val title: String = "",
    val gamersNumber: String = "",
    val duration: String = "",
    val category: String = "",
    val gameId: String = "",
    val photo: String = ""
) : Navigator by DefaultNavigator() {

    fun navigateTo() {
        navigate(NavigationCommand.To(WikipediaFragmentDirections.toWikipediaDetails()))
    }

}