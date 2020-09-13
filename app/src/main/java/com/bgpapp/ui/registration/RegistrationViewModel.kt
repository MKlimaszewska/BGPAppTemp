package com.bgpapp.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.common.DataEventEmitter
import com.bgpapp.common.EventEmitter
import com.bgpapp.common.emit
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import com.bgpapp.service.BGPService
import kotlinx.coroutines.launch
import java.lang.Exception

class RegistrationViewModel(private val service: BGPService): ViewModel(), Navigator by DefaultNavigator() {

    val birthDate = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val photoUrl = MutableLiveData<String>()
    val surname = MutableLiveData<String>()
    val username = MutableLiveData<String>()

    val registerEvent = DataEventEmitter<String>()

    fun onClickToRegister() = viewModelScope.launch {
        try {
            val registerData = RegisterData(
                birthDate = birthDate.value ?: "2020-09-13T09:51:41.935Z",
                email = email.value ?: "a",
                name = name.value ?: "a",
                password = password.value ?: "a",
                photoUrl = photoUrl.value ?: "a",
                username = username.value ?: "a",
                surname = surname.value ?: "a"
            )
            service.register(registerData)

            registerEvent.emit("User registered")

            navigate(NavigationCommand.Back)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun exit() {
        navigate(NavigationCommand.Back)
    }

}