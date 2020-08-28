package com.bgpapp.ui.pubs

import android.util.Log
import androidx.lifecycle.*
import com.bgpapp.common.DataEventEmitter
import com.bgpapp.common.EventEmitter
import com.bgpapp.common.emit
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import com.bgpapp.service.MockedBackend
import com.google.android.gms.maps.model.Marker

class AddPubViewModel : ViewModel(), Navigator by DefaultNavigator() {

    val pubName = MutableLiveData<String>()
    val pubLocation = MutableLiveData<String>()

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

    private fun saveData() {
        val pub = Pub(
            name = pubName.value ?: "",
            address = pubName.value ?: "",
            lat = marker.value?.position?.latitude ?: 0.0,
            lng = marker.value?.position?.longitude ?: 0.0
        )
        //send to service and BE
        MockedBackend.pubs.add(pub)
        navigate(NavigationCommand.Back)
    }

    private fun checkIfValuesAreNotEmpty(): Boolean {
        return pubName.value?.isNotEmpty() ?: false && pubLocation.value?.isNotEmpty() ?: false && marker.value != null
    }

}