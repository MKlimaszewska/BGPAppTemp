package com.bgpapp.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgpapp.R
import com.bgpapp.common.RecyclerAdapter
import com.bgpapp.databinding.FragmentEventsBinding
import com.bgpapp.service.BGPService
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : Fragment() {
    private val service = BGPService()
    private val viewModel = EventsViewModel(service)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentEventsBinding>(inflater, R.layout.fragment_events, container, false).also {
            it.lifecycleOwner = this
            it.viewModel= viewModel
        }.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EventsItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter<EventsItem>(R.layout.events_item)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItems()
    }

}
