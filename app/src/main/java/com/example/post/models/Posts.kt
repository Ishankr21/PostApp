package com.example.post.models

import com.example.post.User

data class Posts(

    val text: String = "",
    val createdBy: User = User(),
    val createdAt: Long = 0L,
    val likedBy: ArrayList<String> = ArrayList())