package data.model

data class MovieDetails(
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val subtitle: String,
    val rating: Double,
    val playTime: Double
)