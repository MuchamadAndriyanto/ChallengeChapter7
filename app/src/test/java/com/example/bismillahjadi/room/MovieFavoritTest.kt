package com.example.bismillahjadi.room

import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class MovieFavoritTest {

    private lateinit var movieFavorit: MovieFavorit

    @Before
    fun setUp() {
        // Persiapan sebelum setiap pengujian dilakukan
        movieFavorit = MovieFavorit(
            id = 1,
            title = "The Movie",
            date = "2023-05-31",
            image = "https://example.com/movie.jpg",
            overview = "This is a great movie!"
        )
    }

    @After
    fun tearDown() {
        // Membersihkan setelah setiap pengujian selesai
    }

    @Test
    fun testMovieFavorit() {
        // Memeriksa apakah nilai yang diatur sesuai dengan yang diharapkan
        assertEquals(1, movieFavorit.id)
        assertEquals("The Movie", movieFavorit.title)
        assertEquals("2023-05-31", movieFavorit.date)
        assertEquals("https://example.com/movie.jpg", movieFavorit.image)
        assertEquals("This is a great movie!", movieFavorit.overview)
    }
}
