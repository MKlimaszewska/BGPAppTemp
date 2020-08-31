package com.bgpapp.ui.profile

import androidx.lifecycle.*
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch

class ProfileViewModel(private val service: BGPService) : ViewModel() {

    private val profileData = MutableLiveData<ProfileData>().apply {
        value = ProfileData()
    }
    private val _avaibleGamesVisible = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val _commentsVisible = MutableLiveData<Boolean>().apply {
        value = false
    }

    val firstName = Transformations.map(profileData) { it.name }
    val surname = Transformations.map(profileData) { it.surname }
    val birthDate = Transformations.map(profileData) { it.birthDate }
    val listOfGames = Transformations.map(profileData) { it.listOfGames }
    val listOfComments = Transformations.map(profileData) { it.listOfComments }
    val phoneNumber = Transformations.map(profileData) { it.phoneNumber }
    val photoUrl = Transformations.map(profileData) { it.photoUrl }
    val userName = Transformations.map(profileData) { it.username }
    val userId = Transformations.map(profileData) { it.userId }
    val listOfMedals = Transformations.map(profileData) { it.listOfMedals }
    val email = Transformations.map(profileData) { it.email }
    val avaibleGamesVisible: LiveData<Boolean> = _avaibleGamesVisible
    val commentsVisible: LiveData<Boolean> = _commentsVisible

    fun loadData() = viewModelScope.launch {
        profileData.value = service.getProfileData()
    }

    fun changeVisibility() {
        val actualValue = _avaibleGamesVisible.value ?: false
        _avaibleGamesVisible.value = !actualValue
    }

    fun changeCommentsVisibility() {
        val actualValue = _commentsVisible.value ?: false
        _commentsVisible.value = !actualValue
    }

}

