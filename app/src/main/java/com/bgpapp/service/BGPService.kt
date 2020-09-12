package com.bgpapp.service

import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.ui.events.EventInformation
import com.bgpapp.ui.events.EventsFragmentDirections
import com.bgpapp.ui.events.EventsItem
import com.bgpapp.ui.profile.CommentItem
import com.bgpapp.ui.profile.ProfileData
import com.bgpapp.ui.pubs.PubsItem
import com.bgpapp.ui.wikipedia.WikipediaFragmentDirections
import com.bgpapp.ui.wikipedia.WikipediaInformation
import com.bgpapp.ui.wikipedia.WikipediaItem
import com.bgpapp.ui.wikipedia.details.WikipediaData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.w3c.dom.Comment

class BGPService {

    suspend fun getWikipediaItems() = withContext(Dispatchers.IO) {
        val wikipedia1 = WikipediaItem(
            "Chińczyk",
            "Liczba graczy: " + "2-4",
            "Czas gry: " + "20-60 min",
            "Kategoria: " + "Towarzyska",
            "1",
            "https://image.ceneostatic.pl/data/products/20040984/i-adamigo-chinczyk.jpg",
            "Chińczyk - gra planszowa przeznaczona dla dwóch, trzehc lub czterech osobnychgraczy czasami rozbija związki!",
            "Wyposażenie: palnsza, 16 pionków, kostka do rzucania"

        )
        val wikipedia2 = WikipediaItem(
        "Warcaby",
        "Liczba graczy: " + "2",
        "Czas gry: " + "20-60 min",
        "Kategoria: " + "Strategiczne",
        "1",
        "https://www.szachowo.pl/images/Foto_szachow/CHW81_warcaby_intarsja_64_pola/watermark/wpx_d5422ad878cd0110af9b4868188a6fcc.jpg"
        )
        val wikipedia3 = WikipediaItem(
        "Szachy",
        "Liczba graczy: " + "2",
        "Czas gry: " + "4-8 h",
        "Kategoria: " + "Strategiczne",
        "1",
        "https://ffstatic.pl/umbra/umbra-szachy-buddy__65443_56ad112-s2500x2500.jpg"
        )
        val navigationCommand1 = NavigationCommand.To(WikipediaFragmentDirections.toWikipediaDetails(wikipedia1))
        val navigationCommand2 = NavigationCommand.To(WikipediaFragmentDirections.toWikipediaDetails(wikipedia2))
        val navigationCommand3 = NavigationCommand.To(WikipediaFragmentDirections.toWikipediaDetails(wikipedia3))
        listOf(
            WikipediaInformation(wikipedia1, navigationCommand1),
            WikipediaInformation(wikipedia2, navigationCommand2),
            WikipediaInformation(wikipedia3, navigationCommand3)
        )
    }

    suspend fun getPubsItems() = withContext(Dispatchers.IO) {
        listOf(PubsItem("Item 1"), PubsItem("Item 2"), PubsItem("Item 3"))
    }

    suspend fun getEventsItems() = withContext(Dispatchers.IO) {
        val item1 = EventsItem("Chińczyk","K6", "08.08", "18:00", "Piotrkowska 6", "", listOf(WikipediaItem("Szachy")), "Turniej")
        val item2 = EventsItem("Rebel Everdell","K6", "08.08", "12:00", "Kołątaja 17", "", emptyList(), "Turniej")
        val item3 = EventsItem("Jumanji","Drzewko", "08.08", "14:30", "Wyścigowa 11/3", "", listOf(WikipediaItem("Chińczyk")), "Spotkanie")
        val navigationCommand1 = NavigationCommand.To(EventsFragmentDirections.toEventDetails(item1))
        listOf(
            EventInformation(item1, navigationCommand1)
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