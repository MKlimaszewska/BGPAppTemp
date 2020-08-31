package com.bgpapp.ui.wikipedia.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch


class WikipediaDetailsViewModel(private val service:BGPService):ViewModel(){
    private val wikipediaDetailsData= MutableLiveData<WikipediaData>().apply {
        value=WikipediaData()
    }
    val gameName= Transformations.map(wikipediaDetailsData){it.title}
    val gamersNumber= Transformations.map(wikipediaDetailsData){it.gamersNumber}
    val duration = Transformations.map(wikipediaDetailsData){it.duration}
    val category= Transformations.map(wikipediaDetailsData){it.category}
    val gameId= Transformations.map(wikipediaDetailsData){it.gameId}
    val description = Transformations.map(wikipediaDetailsData){it.description}
    val moreInfo= Transformations.map(wikipediaDetailsData){it.moreInfo}


    fun loadData()=viewModelScope.launch {
        wikipediaDetailsData.value=service.getWikipediaData()
    }
}

