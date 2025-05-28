package com.example.poppcornapplicationnew

data class Likes(
    val id: Int = 0,
    val contentId: Int,
    val title: String,
    val type: String,
    val liked: Int, // 1 → like, 0 → dislike
    val rating: Double,
    val genres: String // Virgülle ayrılmış liste örn: "Drama,Crime"
)
