package com.bgpapp.ui.events.model

data class Place(
    val addedBy: AddedBy,
    val comments: List<Any>,
    val games: List<Any>,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String
)