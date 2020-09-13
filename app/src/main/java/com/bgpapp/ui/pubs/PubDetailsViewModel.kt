package com.bgpapp.ui.pubs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.service.BGPService
import com.bgpapp.ui.wikipedia.WikipediaItem
import kotlinx.coroutines.launch

class PubDetailsViewModel(private val service: BGPService, private val title: String) : ViewModel() {

    private val _avaibleGamesVisible = MutableLiveData<Boolean>().apply {
        value = false
    }
    val avaibleGamesVisible: LiveData<Boolean> = _avaibleGamesVisible

    val name = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val listOfGames = MutableLiveData<List<WikipediaItem>>().apply {
        value = emptyList()
    }


    fun changeVisibility() {
        val actualValue = _avaibleGamesVisible.value ?: false
        _avaibleGamesVisible.value = !actualValue
    }

    fun getData() = viewModelScope.launch {
        try{
            val pubs = service.getPubs()
            val displayedPub = pubs.filter { it.name == title }.first()

            name.value = displayedPub.name
            address.value = displayedPub.address
            listOfGames.value = getGames(displayedPub.games.map { it.id })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun getGames(gamesId: List<Int>): List<WikipediaItem> {
        if(gamesId.isEmpty()) return emptyList()
        val games = service.getGames()
        val g = mutableListOf<String>()
        gamesId.forEach {gameId ->
            val found = games.find { it.id == gameId }
            if(found != null) {
                g.add(found.name)
            }
        }
        return g.map { WikipediaItem(title = it) }
    }

}