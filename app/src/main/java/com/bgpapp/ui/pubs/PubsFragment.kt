package com.bgpapp.ui.pubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bgpapp.R
import com.bgpapp.databinding.FragmentPubsBinding
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.navigate
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.android.synthetic.main.fragment_pubs.*


class PubsFragment : Fragment() {

    private val service = BGPService()
    private val viewModel = PubsViewModel(service)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.observeNavigation(this)
        return DataBindingUtil.inflate<FragmentPubsBinding>(inflater, R.layout.fragment_pubs, container, false).also {
            it.lifecycleOwner = this
            it.viewModel= viewModel
        }.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItems()
        mapView.onResume()
        lifecycleScope.launchWhenResumed {
            updateMap()
        }
    }

    private suspend fun updateMap() {
        val googleMap = mapView.awaitMap()
        val pubs: List<Pub> = viewModel.getPubs()
        pubs.forEach {
            googleMap.addMarker(MarkerOptions()
                .position(LatLng(it.lat, it.lng))
                .title(it.name)
                .snippet("Address: ${it.address}"))
        }
        googleMap.setOnInfoWindowClickListener {
            navigate(NavigationCommand.To(PubsFragmentDirections.toPubDetailsFragment()))
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.7577506, 19.4344588), 5f))
    }
}
