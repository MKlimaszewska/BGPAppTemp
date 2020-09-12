package com.bgpapp.ui.events

import com.bgpapp.ui.wikipedia.WikipediaItem
import java.io.Serializable

data class EventsItem(
    val title: String,
    val place: String,
    val date: String,
    val time: String,
    val localization: String,
    val photoUrl: String,
    val avaibleGames: List<WikipediaItem>,
    val category: String
): Serializable