package com.bgpapp.ui.wikipedia.model

data class UserId(
    val birthDate: Long,
    val comments: List<CommentX>,
    val email: String,
    val gamesInPossession: List<Any>,
    val id: Int,
    val medals: List<Any>,
    val name: String,
    val photoUrl: Any,
    val surname: String,
    val username: String
)