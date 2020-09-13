package com.bgpapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bgpapp.R
import com.bgpapp.databinding.FragmentLoginBinding
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import com.bgpapp.service.RestService
import com.bgpapp.service.RestServiceBuilder

class LoginFragment : Fragment() {

    private val viewModel = LoginViewModel(BGPService(RestServiceBuilder.build(RestService::class.java)))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.observeNavigation(this)
        return DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

}