package com.bgpapp.ui.wikipedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch

class WikipediaViewModel(private val service: BGPService) : ViewModel() {

    private val _wikipediaItems = MutableLiveData<List<WikipediaItem>>().apply {
        value = emptyList()
    }
    val wikipediaItems: LiveData<List<WikipediaItem>> = _wikipediaItems

    fun getItems() = viewModelScope.launch {
        _wikipediaItems.value = service.getWikipediaItems()
    }

}