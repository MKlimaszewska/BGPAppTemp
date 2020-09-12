package com.bgpapp.ui.wikipedia

import java.io.Serializable

data class WikipediaItem(
    val title: String = "",
    val gamersNumber: String = "",
    val duration: String = "",
    val category: String = "",
    val gameId: String = "",
    val photo: String = "",
    val description: String = "",
    val equipment: String = ""
) : Serializable