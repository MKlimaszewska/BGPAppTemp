package com.bgpapp.ui.events

import androidx.lifecycle.*
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.Navigator
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class EventsViewModel(private val service: BGPService) : ViewModel(),
    Navigator by DefaultNavigator() {

    private val _eventsItems = MutableLiveData<List<EventInformation>>().apply {
        value = emptyList()
    }
    val eventsItems: LiveData<List<EventInformation>> = _eventsItems

    fun getItems(lifecycleOwner: LifecycleOwner) = viewModelScope.launch {
        _eventsItems.value = service.getEventsItems()
        _eventsItems.value?.onEach {
            it.observeNavigation(lifecycleOwner)
        }
    }

    fun sort() {
        val dates = _eventsItems.value?.map { it.eventItem.date } ?: emptyList()
        val times = _eventsItems.value?.map { it.eventItem.time } ?: emptyList()

        val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm")
        val dateInString = mutableListOf<String>()

        for (i in dates.indices) {
            dateInString.add("${dates[i]} ${times[i]}")
        }
        var dateInformationList =
            dateInString.map { DateInformation(it, formatter.parse(it)?.time ?: 0L) }

        //sorting
        dateInformationList=  dateInformationList.sortedBy { it.date }

        val before = _eventsItems.value ?: emptyList()
        val after = mutableListOf<EventInformation>()

        dateInformationList.forEach { dateInformation ->
            val found =
                before.find { "${it.eventItem.date} ${it.eventItem.time}" == dateInformation.dateInString }
            if (found != null) {
                after.add(found)
            }
        }

        _eventsItems.value = after
    }


    fun sortDesc() {
        val dates = _eventsItems.value?.map { it.eventItem.date } ?: emptyList()
        val times = _eventsItems.value?.map { it.eventItem.time } ?: emptyList()

        val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm")
        val dateInString = mutableListOf<String>()

        for (i in dates.indices) {
            dateInString.add("${dates[i]} ${times[i]}")
        }
        var dateInformationList =
            dateInString.map { DateInformation(it, formatter.parse(it)?.time ?: 0L) }
        //sort desc
        dateInformationList=dateInformationList.sortedByDescending { it.date }

        val before = _eventsItems.value ?: emptyList()
        val after = mutableListOf<EventInformation>()

        dateInformationList.forEach { dateInformation ->
            val found =
                before.find { "${it.eventItem.date} ${it.eventItem.time}" == dateInformation.dateInString }
            if (found != null) {
                after.add(found)
            }
        }

        _eventsItems.value = after
    }

    data class DateInformation(
        val dateInString: String,
        val date: Long
    )


    fun dateToLower() {
        sort()
    }

    fun dateToHigher() {
        sortDesc()
    }
}