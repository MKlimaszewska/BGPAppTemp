package com.bgpapp.ui.pubs.model

import com.bgpapp.ui.events.model.UserId

data class CommentX(
    val content: String?,
    val dateAdded: String?,
    val id: Int,
    val userId: UserId?
)