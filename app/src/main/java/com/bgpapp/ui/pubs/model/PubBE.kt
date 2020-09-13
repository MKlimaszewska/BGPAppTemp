package com.bgpapp.ui.pubs.model

data class PubBE(
    val addedBy: AddedBy,
    val comments: List<Any>,
    val games: List<Game>,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val address: String?
)