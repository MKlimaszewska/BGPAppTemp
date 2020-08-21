package com.bgpapp.ui.pubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgpapp.R
import com.bgpapp.common.RecyclerAdapter
import com.bgpapp.databinding.FragmentPubsBinding
import com.bgpapp.service.BGPService
import kotlinx.android.synthetic.main.fragment_pubs.*


class PubsFragment : Fragment() {

    private val service = BGPService()
    private val viewModel = PubsViewModel(service)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentPubsBinding>(inflater, R.layout.fragment_pubs, container, false).also {
            it.lifecycleOwner = this
            it.viewModel= viewModel
        }.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PubsItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter<PubsItem>(R.layout.pubs_item)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItems()
    }

}
