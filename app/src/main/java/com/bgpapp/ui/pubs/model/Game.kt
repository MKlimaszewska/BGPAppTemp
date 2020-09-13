package com.bgpapp.ui.pubs.model

data class Game(
    val category: String,
    val comments: List<CommentX>,
    val duration: String,
    val id: Int,
    val name: String,
    val notes: String,
    val photoUrl: String,
    val playerCount: Int
)