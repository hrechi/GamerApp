package tn.esprit.gamerapp.data

import tn.esprit.gamerapp.R
import tn.esprit.gamerapp.models.Game
import tn.esprit.gamerapp.models.News

object SampleData {
    val games = mutableListOf(
        Game(
            id = 1,
            title = "Red Dead Redemption 2",
            platform = "PS4 Games",
            price = 70.0,
            imageRes = R.drawable.game_rdr2,
            isBookmarked = false
        ),
        Game(
            id = 2,
            title = "Grand Theft Auto 5",
            platform = "PS4 Games",
            price = 30.0,
            imageRes = R.drawable.game_gta5,
            isBookmarked = false
        ),
        Game(
            id = 3,
            title = "The Legend of Zelda",
            platform = "Nintendo Switch",
            price = 90.0,
            imageRes = R.drawable.game_zelda,
            isBookmarked = false
        ),
        Game(
            id = 4,
            title = "God of War Ragnarok",
            platform = "PS4 Games",
            price = 60.0,
            imageRes = R.drawable.game_gow,
            isBookmarked = false
        ),
        Game(
            id = 5,
            title = "Diablo 4",
            platform = "PS4 Games",
            price = 50.0,
            imageRes = R.drawable.game_diablo,
            isBookmarked = false
        ),
        Game(
            id = 6,
            title = "Cyberpunk 2077",
            platform = "PS4 Games",
            price = 45.0,
            imageRes = R.drawable.game_cyberpunk,
            isBookmarked = false
        )
    )

    val news = listOf(
        News(
            id = 1,
            title = "Top 10 Gaming News of the day",
            description = "Here's the latest news from the gaming and e-sports world.",
            imageRes = R.drawable.news_top10
        ),
        News(
            id = 2,
            title = "The Legend of Zelda",
            description = "New updates and DLC announced for the beloved franchise.",
            imageRes = R.drawable.news_zelda
        ),
        News(
            id = 3,
            title = "PlayStation 5 Restock",
            description = "Major retailers announce PS5 availability this week.",
            imageRes = R.drawable.news_ps5
        ),
        News(
            id = 4,
            title = "E-Sports Championship 2024",
            description = "Global tournament dates and prize pool revealed.",
            imageRes = R.drawable.news_esports
        )
    )

    fun getBookmarkedGames(): List<Game> {
        return games.filter { it.isBookmarked }
    }

    fun toggleBookmark(gameId: Int) {
        val game = games.find { it.id == gameId }
        game?.let {
            it.isBookmarked = !it.isBookmarked
        }
    }
}