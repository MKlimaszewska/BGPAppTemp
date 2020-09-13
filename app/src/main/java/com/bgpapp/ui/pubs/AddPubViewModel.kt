package com.bgpapp.ui.pubs

import androidx.lifecycle.*
import com.bgpapp.common.DataEventEmitter
import com.bgpapp.common.emit
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import com.bgpapp.service.BGPService
import com.bgpapp.ui.wikipedia.model.WikipediaBEItem
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.launch

class AddPubViewModel(private val service: BGPService) : ViewModel(), Navigator by DefaultNavigator() {

    val pubName = MutableLiveData<String>()
    val pubLocation = MutableLiveData<String>()
    val avaibleGames = MutableLiveData<String>()

    val marker = MutableLiveData<Marker>()

    val showToastEvent = DataEventEmitter<String>()

    private val isMarkerSet: LiveData<String> = Transformations.map(marker) {
        "Marker set"
    }

    fun save() {
        if(checkIfValuesAreNotEmpty()) {
            saveData()
        } else {
            showToastEvent.emit("Podaj wszystkie potrzebne dane.")
        }
    }

    fun back() {
        navigate(NavigationCommand.Back)
    }

    private fun saveData() = viewModelScope.launch {
        try{
            val games = avaibleGames.value?.split(",") ?: emptyList()
            val gamesBE = service.getGames()
            val g = mutableListOf<WikipediaBEItem>()
            games.forEach {gameName ->
                val found = gamesBE.find { gameFromBackend -> gameFromBackend.name == gameName }
                if(found != null) g.add(found)
            }

            val pub = Pub(
                name = pubName.value ?: "",
                address = pubLocation.value ?: "",
                lat = marker.value?.position?.latitude ?: 0.0,
                lon = marker.value?.position?.longitude ?: 0.0,
                games = g.map { it.id }
            )
            //send to service and BE
            service.addPub(pub)
            navigate(NavigationCommand.Back)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkIfValuesAreNotEmpty(): Boolean {
        return pubName.value?.isNotEmpty() ?: false && pubLocation.value?.isNotEmpty() ?: false && marker.value != null
    }

}