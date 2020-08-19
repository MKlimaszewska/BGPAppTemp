package com.bgpapp.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bgpapp.R
import com.bgpapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private val viewModel = RegistrationViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentRegistrationBinding>(inflater, R.layout.fragment_registration, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

}