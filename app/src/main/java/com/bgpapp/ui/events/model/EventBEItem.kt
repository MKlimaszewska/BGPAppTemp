package com.bgpapp.ui.events.model

data class EventBEItem(
    val availableGames: List<AvailableGame>,
    val id: Int,
    val title: String?,
    val medals: Any,
    val meetingDate: Long,
    val meetingType: String,
    val organizer: Organizer,
    val place: Place
)