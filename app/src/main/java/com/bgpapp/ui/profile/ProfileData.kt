package com.bgpapp.ui.profile

import com.bgpapp.ui.wikipedia.WikipediaItem

data class ProfileData(
    val name: String="",
    val surname: String="",
    val listOfGames: List<WikipediaItem> = emptyList(),
    val listOfComments: List<CommentItem> = emptyList(),
    val listOfMedals: List<String> = emptyList(),
    val birthDate: String="",
    val phoneNumber: String="",
    val email: String="",
    val userId: String="",
    val username: String="",
    val photoUrl: String=""
)
