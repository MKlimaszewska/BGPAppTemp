package com.bgpapp.ui.login

import com.bgpapp.ui.pubs.model.CommentX
import com.bgpapp.ui.pubs.model.Game

data class UserInfo(
    val birthDate: Long,
    val comments: List<CommentX>,
    val email: String,
    val gamesInPossession: List<Game>,
    val id: Int,
    val medals: List<Any>,
    val name: String,
    val photoUrl: String,
    val surname: String,
    val username: String
)