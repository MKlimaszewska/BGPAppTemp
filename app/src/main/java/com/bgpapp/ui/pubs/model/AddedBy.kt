package com.bgpapp.ui.pubs.model

data class AddedBy(
    val birthDate: Long,
    val comments: List<Comment>,
    val email: String,
    val gamesInPossession: List<Any>,
    val id: Int,
    val medals: Any,
    val name: String,
    val photoUrl: Any,
    val surname: String,
    val username: String
)