package com.bgpapp.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch

class EventsViewModel(private val service: BGPService) : ViewModel() {

    private val _eventsItems = MutableLiveData<List<EventsItem>>().apply {
        value = emptyList()
    }
    val EventsItems: LiveData<List<EventsItem>> = _eventsItems

    fun getItems() = viewModelScope.launch {
        _eventsItems.value = service.getEventsItems()
    }

}