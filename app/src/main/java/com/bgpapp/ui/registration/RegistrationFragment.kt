package com.bgpapp.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bgpapp.R
import com.bgpapp.common.EventObserver
import com.bgpapp.databinding.FragmentRegistrationBinding
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import com.bgpapp.service.RestService
import com.bgpapp.service.RestServiceBuilder

class RegistrationFragment : Fragment() {

    private val viewModel = RegistrationViewModel(BGPService(RestServiceBuilder.build(RestService::class.java)))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.observeNavigation(this)
        return DataBindingUtil.inflate<FragmentRegistrationBinding>(inflater, R.layout.fragment_registration, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.registerEvent.observe(this, EventObserver{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

    }

}