package com.bgpapp.ui.events

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgpapp.R
import com.bgpapp.common.RecyclerAdapter
import com.bgpapp.databinding.FragmentEventsBinding
import com.bgpapp.navigation.NavigationCommand
import com.bgpapp.navigation.navigate
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import com.bgpapp.service.RestService
import com.bgpapp.service.RestServiceBuilder
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : Fragment() {
    private val service = BGPService(RestServiceBuilder.build(RestService::class.java))
    private val viewModel = EventsViewModel(service)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.observeNavigation(this)
        return DataBindingUtil.inflate<FragmentEventsBinding>(
            inflater,
            R.layout.fragment_events,
            container,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EventsItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter<EventsItem>(R.layout.events_item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.filter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout->{
                navigate(NavigationCommand.FinishFlow)
                return true
            }
            R.id.dateToLower -> {
                viewModel.dateToLower()
                return true
            }
            R.id.DateToHigher->{
                viewModel.dateToHigher()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItems(this)
    }

}
