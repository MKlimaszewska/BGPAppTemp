package com.bgpapp.ui.events.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bgpapp.ui.wikipedia.WikipediaItem

class EventDetailsViewModel : ViewModel() {

    private val _avaibleGamesVisible = MutableLiveData<Boolean>().apply {
        value = false
    }
    val avaibleGamesVisible: LiveData<Boolean> = _avaibleGamesVisible
    val listOfGames = listOf<WikipediaItem>(WikipediaItem("Chińczyk"), WikipediaItem("Szachy"), WikipediaItem("Warcaby"))

    fun changeVisibility() {
        val actualValue = _avaibleGamesVisible.value ?: false
        _avaibleGamesVisible.value = !actualValue
    }

}