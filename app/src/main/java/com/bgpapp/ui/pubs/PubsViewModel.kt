package com.bgpapp.ui.pubs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch

class PubsViewModel(private val service: BGPService) : ViewModel() {

    private val _pubsItems = MutableLiveData<List<PubsItem>>().apply {
        value = emptyList()
    }
    val pubsItems: LiveData<List<PubsItem>> = _pubsItems

    fun getItems() = viewModelScope.launch {
        _pubsItems.value = service.getPubsItems()
    }

}