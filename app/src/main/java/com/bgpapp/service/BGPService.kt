package com.bgpapp.service

import com.bgpapp.ui.events.EventsItem
import com.bgpapp.ui.profile.CommentItem
import com.bgpapp.ui.profile.ProfileData
import com.bgpapp.ui.pubs.PubsItem
import com.bgpapp.ui.wikipedia.WikipediaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.w3c.dom.Comment

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

    suspend fun getProfileData() = withContext(Dispatchers.IO) {
        ProfileData(
            "Zbyszek",
            "Ciasteczkowy",
            listOf(WikipediaItem("Munchkin")),
            listOf(
                CommentItem("007", "Lorem input blablabla", "Ziutek", ""),
                CommentItem("007", "Lorem input blablabla", "Ziutek", "")
            ),
            listOf(""),
            "20200101",
            "123456789",
            "gmail@gmail.com",
            "008",
            "Omaz",
            ""
        )
    }
}