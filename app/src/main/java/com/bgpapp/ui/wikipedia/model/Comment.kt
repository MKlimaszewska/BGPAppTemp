package com.bgpapp.ui.wikipedia.model

data class Comment(
    val content: String,
    val dateAdded: Long,
    val id: Int,
    val userId: UserId
)