package com.bgpapp.ui.wikipedia.details

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
import com.bgpapp.databinding.FragmentProfileBinding
import com.bgpapp.databinding.FragmentWikipediaDetailsBinding
import com.bgpapp.service.BGPService
import com.bgpapp.ui.profile.CommentItem
import com.bgpapp.ui.profile.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class WikipediaDetailsFragment : Fragment() {

    private lateinit var viewModel: WikipediaDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: WikipediaDetailsFragmentArgs by navArgs()
        viewModel= WikipediaDetailsViewModel(args.wikipediaItem)
        return DataBindingUtil.inflate<FragmentWikipediaDetailsBinding>(inflater,R.layout.fragment_wikipedia_details,container,false).also {
            it.lifecycleOwner=this
            it.viewModel=viewModel
        }.root
    }
}