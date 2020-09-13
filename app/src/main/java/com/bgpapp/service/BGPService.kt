package com.bgpapp.service

import android.util.Log
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.ui.addevent.model.EventToAdd
import com.bgpapp.ui.events.EventInformation
import com.bgpapp.ui.events.EventsFragmentDirections
import com.bgpapp.ui.events.EventsItem
import com.bgpapp.ui.events.model.EventBEItem
import com.bgpapp.ui.login.LoginResponse
import com.bgpapp.ui.login.UserToLoginData
import com.bgpapp.ui.profile.CommentItem
import com.bgpapp.ui.profile.ProfileData
import com.bgpapp.ui.pubs.Pub
import com.bgpapp.ui.pubs.model.PubBE
import com.bgpapp.ui.registration.RegisterData
import com.bgpapp.ui.wikipedia.WikipediaFragmentDirections
import com.bgpapp.ui.wikipedia.WikipediaInformation
import com.bgpapp.ui.wikipedia.WikipediaItem
import com.bgpapp.ui.wikipedia.details.WikipediaData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class BGPService(private val rest: RestService) {

    suspend fun getWikipediaItems() = withContext(Dispatchers.IO) {
        val items = rest.getGames()
        val wikipediaItems = items.map { WikipediaItem(it.name, it.playerCount.toString(), it.duration, it.category, it.id.toString(), it.photoUrl, "desc", it.notes) }
        val wikipediaInformation = wikipediaItems.map { WikipediaInformation(it, NavigationCommand.To(WikipediaFragmentDirections.toWikipediaDetails(it))) }
        wikipediaInformation
    }

    suspend fun getGames() = withContext(Dispatchers.IO) {
        rest.getGames()
    }

    suspend fun getPubsItems() = withContext(Dispatchers.IO) {
        val pubs = rest.getPubs()
        val p = pubs.map { Pub(it.name, it.address ?: "Nie ma :)", it.lat, it.lon, it.games.map { it.id }) }
        p
    }

    suspend fun getEventsItems(): List<EventInformation> = withContext(Dispatchers.IO) {

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy hh:mm")

        val list: List<EventBEItem> = rest.getEvents()
        val items = list.map { EventsItem(
            title = it.title ?: "Brak tytulu",
            place = it.place.name,
            date = prepareDate(Date(it.meetingDate), simpleDateFormat),
            time = prepareHour(Date(it.meetingDate), simpleDateFormat),
            localization = it.place.name,
            photoUrl = "",
            avaibleGames = it.availableGames.map { WikipediaItem(it.name) },
            category = it.meetingType
        ) }
        val toReturn: List<EventInformation> = items.map {
            EventInformation(it, NavigationCommand.To(EventsFragmentDirections.toEventDetails(it)))
        }
        toReturn
    }

    fun prepareDate(date: Date, format: SimpleDateFormat): String {
        val stringDate = format.format(date)
        return stringDate.split(" ")[0]
    }

    private fun prepareHour(date: Date, format: SimpleDateFormat): String {
        val stringDate = format.format(date)
        return stringDate.split(" ")[1]
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
        val response = rest.getPubs()
        response
    }

    fun findPubByName(pubs: List<PubBE>, name: String): PubBE {
        val pub = pubs.filter { it.name == name }
        return pub.first()
    }

    suspend fun register(registerData: RegisterData) {
        val response = rest.registerUser(registerData)
        Log.i("supertest123", response.toString())
    }

    suspend fun login(loginData: UserToLoginData) {
        val response: LoginResponse = rest.loginUser(loginData)
        Config.token = response.accessToken
        Config.loggedId = response.userInfo
        Log.i("supertest123 login", Config.token)
    }

    suspend fun addEvent(eventToAdd: EventToAdd) {
        val response = rest.addEvent(eventToAdd)
        Log.i("supertest123", response.toString())
    }

    suspend fun addPub(pub: Pub) {
        val response = rest.addPub(pub)
        Log.i("supertest123", response.toString())
    }


}