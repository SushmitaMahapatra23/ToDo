package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ToDo(
    val id:String,
    val userEmail:String,
    val description: String,
    val date: Long
)
