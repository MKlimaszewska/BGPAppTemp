package com.bgpapp.ui.profile

import androidx.lifecycle.*
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import com.bgpapp.service.BGPService
import com.bgpapp.service.Config
import com.bgpapp.ui.wikipedia.WikipediaItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ProfileViewModel(private val service: BGPService) : ViewModel(), Navigator by DefaultNavigator() {

    private val profileData = MutableLiveData<ProfileData>().apply {
        value = ProfileData()
    }
    private val _avaibleGamesVisible = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val _commentsVisible = MutableLiveData<Boolean>().apply {
        value = false
    }

    val firstName = MutableLiveData<String>()
    val surname = MutableLiveData<String>()
    val birthDate = MutableLiveData<String>()
    val listOfGames = MutableLiveData<List<WikipediaItem>>().apply {
        value = emptyList()
    }
    val listOfComments = MutableLiveData<List<CommentItem>>().apply {
        value = emptyList()
    }
    val photoUrl = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val userId = MutableLiveData<String>()
    val listOfMedals = emptyList<String>()
    val email = MutableLiveData<String>()

    val avaibleGamesVisible: LiveData<Boolean> = _avaibleGamesVisible
    val commentsVisible: LiveData<Boolean> = _commentsVisible

    fun loadData() = viewModelScope.launch {
        firstName.value = Config.loggedId.name
        surname.value = Config.loggedId.surname

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy hh:mm")

        birthDate.value = service.prepareDate(Date(Config.loggedId.birthDate), simpleDateFormat)
        photoUrl.value = Config.loggedId.photoUrl
        userName.value = Config.loggedId.username
        email.value = Config.loggedId.email
        listOfGames.value = Config.loggedId.gamesInPossession.map { WikipediaItem(it.name) }
        listOfComments.value = Config.loggedId.comments.map { CommentItem(comment = it.content ?: "Pusty komentarz", userName = it.userId?.name ?: "") }
    }

    fun changeVisibility() {
        val actualValue = _avaibleGamesVisible.value ?: false
        _avaibleGamesVisible.value = !actualValue
    }

    fun changeCommentsVisibility() {
        val actualValue = _commentsVisible.value ?: false
        _commentsVisible.value = !actualValue
    }

    fun toSettings() {
        navigate(NavigationCommand.To(ProfileFragmentDirections.toProfileSettings()))
    }

}

