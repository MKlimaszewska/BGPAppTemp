package com.bgpapp.ui.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgpapp.R
import com.bgpapp.common.RecyclerAdapter
import com.bgpapp.databinding.FragmentProfileBinding
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private val viewModel = ProfileViewModel(BGPService())
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.observeNavigation(this)
        return DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userComments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter<CommentItem>(R.layout.comments_item)
        }

        userGames.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter<GameItem>(R.layout.games_item)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }


}
