package com.bgpapp.ui.addevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import com.bgpapp.service.BGPService
import com.bgpapp.ui.addevent.model.EventToAdd
import com.bgpapp.ui.addevent.model.Organizer
import com.bgpapp.ui.pubs.model.PubBE
import com.bgpapp.ui.wikipedia.model.WikipediaBEItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddEventViewModel(private val service: BGPService) : ViewModel(), Navigator by DefaultNavigator() {

    val title = MutableLiveData<String>()
    val localization = MutableLiveData<String>()
    val typeOfMeeting = MutableLiveData<String>()
    val avaibleGames = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val hour = MutableLiveData<String>()

    fun updateDateOfBirth(year: Int, month: Int, dayOfMonth: Int) {
        val formatted = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        date.value = formatted.format(Date(calendar.timeInMillis))
    }

    fun updateHour(hourOfDay: Int, minute: Int) {
        hour.value = "$hourOfDay:$minute"
    }

    fun addEvent() = viewModelScope.launch {
        try{
            val pubs: List<PubBE> = service.getPubs()
            val id = pubs.find { it.name == localization.value!! }?.id ?: 0

            val games = avaibleGames.value?.split(",") ?: emptyList()
            val gamesBE = service.getGames()
            val g = mutableListOf<WikipediaBEItem>()
            games.forEach {gameName ->
                val found = gamesBE.find { gameFromBackend -> gameFromBackend.name == gameName }
                if(found != null) g.add(found)
            }

            val eventToAdd = EventToAdd(
                availableGames=g.map { it.id } ,
                title = title.value ?: "",
                meetingDate = date.value ?: "",
                meetingType = typeOfMeeting.value ?: "",
                placeId = id
            )


            service.addEvent(eventToAdd)
            navigate(NavigationCommand.Back)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getPubs(): List<String> {
        val pubs = service.getPubs()
        return pubs.map { it.name }
    }

}