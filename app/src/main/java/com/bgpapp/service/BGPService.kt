package com.bgpapp.service

import com.bgpapp.ui.events.EventsItem
import com.bgpapp.ui.profile.CommentItem
import com.bgpapp.ui.profile.ProfileData
import com.bgpapp.ui.pubs.PubsItem
import com.bgpapp.ui.wikipedia.WikipediaItem
import com.bgpapp.ui.wikipedia.details.WikipediaData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.w3c.dom.Comment

class BGPService {

    suspend fun getWikipediaItems() = withContext(Dispatchers.IO) {
        listOf(
            WikipediaItem(
                "Chińczyk",
                "Liczba graczy: " + "2-4",
                "Czas gry: " + "20-60 min",
                "Kategoria: " + "Losowa",
                "1"

            ), WikipediaItem(
                "Chińczyk",
                "Liczba graczy: " + "2-4",
                "Czas gry: " + "20-60 min",
                "Kategoria: " + "Losowa",
                "1"

            ), WikipediaItem(
                "Chińczyk",
                "Liczba graczy: " + "2-4",
                "Czas gry: " + "20-60 min",
                "Kategoria: " + "Losowa",
                "1"

            )
        )
    }

    suspend fun getPubsItems() = withContext(Dispatchers.IO) {
        listOf(PubsItem("Item 1"), PubsItem("Item 2"), PubsItem("Item 3"))
    }

    suspend fun getEventsItems() = withContext(Dispatchers.IO) {
        listOf(
            EventsItem("Chińczyk","K6", "08.08", "18:00", "Piotrkowska 6", ""),
            EventsItem("Chińczyk2","K6", "08.08", "18:00", "Piotrkowska 6", ""),
            EventsItem("Chińczyk3","K6", "08.08", "18:00", "Piotrkowska 6", "")
        )
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

    suspend fun getWikipediaData() = withContext(Dispatchers.IO) {
        WikipediaData(
            "Chińczyk",
            "Liczba graczy: " + "2-4",
            "Czas gry: " + "20-40 min",
            "Kategoria: " + "Losowa",
            "1",
            "Chińczyk – gra planszowa przeznaczona dla dwóch, trzech lub czterech osóbich.\n" +
                    "Czasami rozbija związki! ",
            "Wyposażenie: \nplansza, \n16 pionków, \nkostka do rzucania"


        )
    }

    suspend fun getPubs() = withContext(Dispatchers.IO) {
        MockedBackend.pubs
    }


}