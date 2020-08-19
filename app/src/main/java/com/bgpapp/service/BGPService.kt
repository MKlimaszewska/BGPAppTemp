package com.bgpapp.service

import com.bgpapp.ui.wikipedia.WikipediaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BGPService {

    suspend fun getWikipediaItems() = withContext(Dispatchers.IO) {
        listOf(WikipediaItem("Item 1"), WikipediaItem("Item 2"), WikipediaItem("Item 3"))
    }

}