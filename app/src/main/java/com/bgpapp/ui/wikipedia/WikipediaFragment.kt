package com.bgpapp.ui.wikipedia


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgpapp.R
import com.bgpapp.common.RecyclerAdapter
import com.bgpapp.databinding.FragmentWikipediaBinding
import com.bgpapp.service.BGPService
import kotlinx.android.synthetic.main.fragment_wikipedia.*

class WikipediaFragment : Fragment() {

    private val service = BGPService()
    private val viewModel = WikipediaViewModel(service)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentWikipediaBinding>(inflater, R.layout.fragment_wikipedia, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wikipediaItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter<WikipediaItem>(R.layout.wikipedia_item)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItems(this)
    }

}
