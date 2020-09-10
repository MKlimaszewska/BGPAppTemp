package com.bgpapp.service

import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.ui.events.EventsFragmentDirections
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
                "Kategoria: " + "Towarzyska",
                "1",
                "https://image.ceneostatic.pl/data/products/20040984/i-adamigo-chinczyk.jpg"

            ), WikipediaItem(
                "Warcaby",
                "Liczba graczy: " + "2",
                "Czas gry: " + "20-60 min",
                "Kategoria: " + "Strategiczne",
                "1",
                "https://www.szachowo.pl/images/Foto_szachow/CHW81_warcaby_intarsja_64_pola/watermark/wpx_d5422ad878cd0110af9b4868188a6fcc.jpg"
            ), WikipediaItem(
                "Szachy",
                "Liczba graczy: " + "2",
                "Czas gry: " + "4-8 h",
                "Kategoria: " + "Strategiczne",
                "1",
                "https://ffstatic.pl/umbra/umbra-szachy-buddy__65443_56ad112-s2500x2500.jpg"
            )
        )
    }

    suspend fun getPubsItems() = withContext(Dispatchers.IO) {
        listOf(PubsItem("Item 1"), PubsItem("Item 2"), PubsItem("Item 3"))
    }

    suspend fun getEventsItems() = withContext(Dispatchers.IO) {
        val navigationCommand = NavigationCommand.To(EventsFragmentDirections.toEventDetails())
        listOf(
            EventsItem("Chińczyk","K6", "08.08", "18:00", "Piotrkowska 6", "", navigationCommand),
            EventsItem("Rebel Everdell","K6", "08.08", "12:00", "Kołątaja 17", "", navigationCommand),
            EventsItem("Jumanji","Drzewko", "08.08", "14:30", "Wyścigowa 11/3", "", navigationCommand)
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
            "Czas gry: " + "20-60 min",
            "Kategoria: " + "Towarzyska",
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