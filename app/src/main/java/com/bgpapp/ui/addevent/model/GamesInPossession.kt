package com.bgpapp.ui.addevent.model

data class GamesInPossession(
    val category: String,
    val comments: List<CommentX>,
    val duration: String,
    val id: Int,
    val name: String,
    val notes: String,
    val photoUrl: String,
    val playerCount: Int
)