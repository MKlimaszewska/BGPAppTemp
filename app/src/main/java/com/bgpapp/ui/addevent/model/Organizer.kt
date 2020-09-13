package com.bgpapp.ui.addevent.model

data class Organizer(
    val birthDate: String,
    val comments: List<Comment>,
    val email: String,
    val gamesInPossession: List<GamesInPossession>,
    val id: Int,
    val medals: List<String>,
    val name: String,
    val photoUrl: String,
    val surname: String,
    val username: String
)