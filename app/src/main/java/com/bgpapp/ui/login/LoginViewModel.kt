package com.bgpapp.ui.login

import androidx.lifecycle.ViewModel
import com.bgpapp.navigation.DefaultNavigator
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.Navigator
import com.bgpapp.ui.MainActivity

class LoginViewModel : ViewModel(), Navigator by DefaultNavigator() {

    val buttonText = "Zaloguj"

    fun onClickToLogIn() {
        navigate(NavigationCommand.ToFlow(MainActivity::class.java))

    }

    fun onClickToRegistration() {
        navigate(
            NavigationCommand.To(
                LoginFragmentDirections.toregistrationfragment()
            )
        )
    }

}
