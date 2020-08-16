package com.bgpApp.ui.login

import androidx.lifecycle.ViewModel
import com.bgpApp.navigation.DefaultNavigator
import com.bgpApp.navigation.NavigationCommand
import com.bgpApp.navigation.Navigator
import com.bgpApp.ui.MainActivity

class LoginViewModel : ViewModel(), Navigator by DefaultNavigator() {

    val buttonText = "Zaloguj"

    fun onClick() {
        navigate(NavigationCommand.ToFlow(MainActivity::class.java))
        //navigate(NavigationCommand.To(LoginFragmentDirections.toRegistrationFragment()))
    }

}