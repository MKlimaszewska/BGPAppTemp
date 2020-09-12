package com.bgpapp.ui.wikipedia.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.service.BGPService
import com.bgpapp.ui.wikipedia.WikipediaItem
import kotlinx.coroutines.launch


class WikipediaDetailsViewModel(private val wikipediaItem: WikipediaItem) : ViewModel() {
    val gameName = wikipediaItem.title
    val gamersNumber = wikipediaItem.gamersNumber
    val duration = wikipediaItem.duration
    val category = wikipediaItem.category
    val gameId = wikipediaItem.gameId
    val description = wikipediaItem.description
    val moreInfo = wikipediaItem.equipment
}

