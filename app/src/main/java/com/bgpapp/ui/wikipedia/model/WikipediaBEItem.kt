package com.bgpapp.ui.wikipedia.model

data class WikipediaBEItem(
    val category: String,
    val comments: List<Comment>,
    val duration: String,
    val id: Int,
    val name: String,
    val notes: String,
    val photoUrl: String,
    val playerCount: Int
)