package com.bgpApp.ui.login

import androidx.lifecycle.ViewModel
import com.bgpApp.navigation.DefaultNavigator
import com.bgpApp.navigation.NavigationCommand
import com.bgpApp.navigation.Navigator

class LoginViewModel : ViewModel(), Navigator by DefaultNavigator() {

    val buttonText = "super tekst 123"

    fun onClick() {
        navigate(NavigationCommand.To(LoginFragmentDirections.toRegistrationFragment()))
    }

}