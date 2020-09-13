package com.bgpapp.ui.events

import androidx.lifecycle.*
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.Navigator
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch

class EventsViewModel(private val service: BGPService) : ViewModel(), Navigator by DefaultNavigator() {

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
}