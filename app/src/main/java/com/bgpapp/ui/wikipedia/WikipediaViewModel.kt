package com.bgpapp.ui.wikipedia

import androidx.lifecycle.*
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch

class WikipediaViewModel(private val service: BGPService) : ViewModel() {

    private val _wikipediaItems = MutableLiveData<List<WikipediaItem>>().apply {
        value = emptyList()
    }
    val wikipediaItems: LiveData<List<WikipediaItem>> = _wikipediaItems

    fun getItems(lifecycleOwner: LifecycleOwner) = viewModelScope.launch {
        _wikipediaItems.value = service.getWikipediaItems()
        observeItemsNavigation(lifecycleOwner)
    }

    private fun observeItemsNavigation(lifecycleOwner: LifecycleOwner) {
        _wikipediaItems.value?.forEach {
            it.observeNavigation(lifecycleOwner)
        }
    }

}