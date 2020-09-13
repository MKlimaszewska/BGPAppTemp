package com.bgpapp.ui.pubs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import com.bgpapp.service.BGPService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PubsViewModel(private val service: BGPService) : ViewModel(), Navigator by DefaultNavigator() {

    private val _pubsItems = MutableLiveData<List<PubsItem>>().apply {
        value = emptyList()
    }
    val pubsItems: LiveData<List<PubsItem>> = _pubsItems

    fun navigateToAddPub() {
        navigate(NavigationCommand.To(PubsFragmentDirections.toAddPubFragment()))
    }

    suspend fun getPubs(): List<Pub> = withContext(Dispatchers.IO) {
        return@withContext try{
            service.getPubsItems()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<Pub>()
        }
    }

}