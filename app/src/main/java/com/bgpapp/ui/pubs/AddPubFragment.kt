package com.bgpapp.ui.pubs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bgpapp.R
import com.bgpapp.common.EventObserver
import com.bgpapp.databinding.FragmentAddPubBinding
import com.bgpapp.navigation.observeNavigation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.android.synthetic.main.fragment_add_pub.*

class AddPubFragment : Fragment() {

    private val viewModel = AddPubViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.observeNavigation(this)
        return DataBindingUtil.inflate<FragmentAddPubBinding>(inflater, R.layout.fragment_add_pub, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPubMap.onCreate(savedInstanceState)

        viewModel.showToastEvent.observe(this, EventObserver{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        lifecycleScope.launchWhenCreated {
            val googleMap = addPubMap.awaitMap()

            googleMap.apply {

                setOnMapClickListener {
                    if(viewModel.marker.value != null) {
                        viewModel.marker.value!!.remove()
                    }
                    val marker = addMarker(
                        MarkerOptions()
                            .position(it)
                    )
                    viewModel.marker.value = marker
                }
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.7577506, 19.4344588), 5f))
        }

        Toast.makeText(requireContext(), "Kliknij w miejsce, gdzie znajduje się pub, który chcesz dodać.", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        addPubMap.onResume()
    }

}