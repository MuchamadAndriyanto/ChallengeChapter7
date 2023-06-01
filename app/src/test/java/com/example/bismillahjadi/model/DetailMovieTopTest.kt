package com.example.bismillahjadi.model

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailMovieTopTest {

    private lateinit var detailMovieTop: DetailMovieTop

    @Before
    fun setUp() {
        // Persiapan sebelum setiap pengujian dilakukan
        val id = 1
        val imagepath = "path/to/image"
        val title = "Movie Title"
        val date = "2023-05-31"
        val overview = "This is a movie overview"

        // Membuat objek DetailMovieTop dengan nilai-nilai yang diberikan
        detailMovieTop = DetailMovieTop(id, imagepath, title, date, overview)
    }

    @After
    fun tearDown() {
        // Membersihkan setelah setiap pengujian selesai
    }

    @Test
    fun testDetailMovieTop() {
        // Persiapan data yang diperlukan untuk pengujian
        val id = 1
        val imagepath = "path/to/image"
        val title = "Movie Title"
        val date = "2023-05-31"
        val overview = "This is a movie overview"

        // Memeriksa apakah nilai-nilai objek DetailMovieTop sesuai dengan yang diharapkan
        assertEquals(id, detailMovieTop.id)
        assertEquals(imagepath, detailMovieTop.imagepath)
        assertEquals(title, detailMovieTop.title)
        assertEquals(date, detailMovieTop.date)
        assertEquals(overview, detailMovieTop.overview)
    }
}
