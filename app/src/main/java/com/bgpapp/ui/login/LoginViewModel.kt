package com.bgpapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import com.bgpapp.service.BGPService
import com.bgpapp.ui.MainActivity
import kotlinx.coroutines.launch

class LoginViewModel(private val service: BGPService) : ViewModel(), Navigator by DefaultNavigator() {

    val username = MutableLiveData<String>().apply {
        value = "b"
    }
    val password = MutableLiveData<String>().apply {
        value = "b"
    }

    fun onClickToLogIn() = viewModelScope.launch {
        try {
            val loginData = UserToLoginData(password.value ?: "", username.value ?: "")
            service.login(loginData)
            navigate(NavigationCommand.ToFlow(MainActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onClickToRegistration() {
        navigate(
            NavigationCommand.To(
                LoginFragmentDirections.toregistrationfragment()
            )
        )
    }

}
