package com.bgpapp.ui.events.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bgpapp.ui.events.EventsItem
import com.bgpapp.ui.wikipedia.WikipediaItem

class EventDetailsViewModel(eventsItem: EventsItem) : ViewModel() {

    val title = eventsItem.title
    val date = eventsItem.date
    val place = eventsItem.place
    val address = eventsItem.localization
    val avaibleGames = eventsItem.avaibleGames
    val category = eventsItem.category
    val _avaibleGamesVisible = MutableLiveData<Boolean>().apply {
        value = false
    }
    val avaibleGamesVisible: LiveData<Boolean> = _avaibleGamesVisible

    fun changeVisibility() {
        val actualValue = _avaibleGamesVisible.value ?: false
        _avaibleGamesVisible.value = !actualValue
    }

}