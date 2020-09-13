package com.bgpapp.ui.registration

data class CreatedUser(
    val birthDate: Long,
    val comments: Any,
    val email: String,
    val gamesInPossession: Any,
    val id: Int,
    val medals: Any,
    val name: String,
    val photoUrl: String,
    val surname: String,
    val username: String
)