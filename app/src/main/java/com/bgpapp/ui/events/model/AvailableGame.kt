package com.bgpapp.ui.events.model

data class AvailableGame(
    val category: String,
    val comments: List<Comment>,
    val duration: String,
    val id: Int,
    val name: String,
    val notes: String,
    val photoUrl: String,
    val playerCount: Int
)