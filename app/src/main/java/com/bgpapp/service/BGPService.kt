package com.bgpapp.service

import com.bgpapp.ui.events.EventsItem
import com.bgpapp.ui.pubs.PubsItem
import com.bgpapp.ui.wikipedia.WikipediaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BGPService {

    suspend fun getWikipediaItems() = withContext(Dispatchers.IO) {
        listOf(WikipediaItem("Item 1"), WikipediaItem("Item 2"), WikipediaItem("Item 3"))
    }

    suspend fun getPubsItems() = withContext(Dispatchers.IO) {
        listOf(PubsItem("Item 1"), PubsItem("Item 2"), PubsItem("Item 3"))
    }

    suspend fun getEventsItems() = withContext(Dispatchers.IO) {
        listOf(EventsItem("Item 1"), EventsItem("Item 2"), EventsItem("Item 3"))
    }


}