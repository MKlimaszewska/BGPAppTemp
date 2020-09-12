package com.bgpapp.ui.events.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgpapp.R
import com.bgpapp.common.RecyclerAdapter
import com.bgpapp.databinding.FragmentEventsDetailsBinding
import com.bgpapp.ui.profile.GameItem
import kotlinx.android.synthetic.main.fragment_events_details.*

class EventsDetailsFragment : Fragment() {

    private lateinit var viewModel: EventDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args: EventsDetailsFragmentArgs by navArgs()
        viewModel = EventDetailsViewModel(args.eventsItem)

        return DataBindingUtil.inflate<FragmentEventsDetailsBinding>(inflater, R.layout.fragment_events_details, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userGames.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter<GameItem>(R.layout.games_item)
        }

    }

}