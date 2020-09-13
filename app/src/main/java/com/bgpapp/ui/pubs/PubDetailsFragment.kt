package com.bgpapp.ui.pubs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgpapp.R
import com.bgpapp.common.RecyclerAdapter
import com.bgpapp.databinding.FragmentPubDetailsBinding
import com.bgpapp.databinding.FragmentPubDetailsBindingImpl
import com.bgpapp.service.BGPService
import com.bgpapp.service.RestService
import com.bgpapp.service.RestServiceBuilder
import com.bgpapp.ui.profile.GameItem
import kotlinx.android.synthetic.main.fragment_pub_details.*

class PubDetailsFragment : Fragment() {

    private lateinit var viewModel: PubDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args: PubDetailsFragmentArgs by navArgs()
        viewModel = PubDetailsViewModel(BGPService(RestServiceBuilder.build(RestService::class.java)), args.title)
        return DataBindingUtil.inflate<FragmentPubDetailsBinding>(inflater, R.layout.fragment_pub_details, container, false).also {
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

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

}