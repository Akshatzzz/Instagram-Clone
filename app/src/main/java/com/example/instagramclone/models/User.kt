package com.example.instagramclone.models

data class User(
    val name: String,
    val email: String,
    val password: String,
    val imageURI: String? = null
)
