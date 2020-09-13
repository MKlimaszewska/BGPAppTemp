package com.bgpapp.ui.addevent.model

data class EventToAdd(
    val availableGames: List<Int>,
    val title: String,
    val meetingDate: String,
    val meetingType: String,
    val placeId: Int
)