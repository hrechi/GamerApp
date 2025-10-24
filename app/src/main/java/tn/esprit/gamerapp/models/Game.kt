package tn.esprit.gamerapp.models

data class Game(
    val id: Int,
    val title: String,
    val platform: String,
    val price: Double,
    val imageRes: Int,
    var isBookmarked: Boolean = false
)