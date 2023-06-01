package com.example.bismillahjadi.model

import com.google.gson.Gson
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class ListMovieTest {
    private lateinit var gson: Gson

    @Before
    fun setup() {
        gson = Gson()
    }

    @After
    fun teardown() {
        // Clear any resources or perform cleanup after the test
    }

    @Test
    fun testListMovieDeserialization() {
        // Menyiapkan data JSON yang akan di-deserialize
        val json = """
            {
                "page": 1,
                "results": [
                    {
                        "id": 1,
                        "title": "Movie 1"
                    },
                    {
                        "id": 2,
                        "title": "Movie 2"
                    }
                ],
                "total_pages": 3,
                "total_results": 6
            }
        """.trimIndent()

        // Melakukan deserialisasi dari JSON ke objek ListMovie menggunakan Gson
        val listMovie = gson.fromJson(json, ListMovie::class.java)

        // Memeriksa apakah nilai-nilai yang di-deserialize sesuai dengan yang diharapkan
        assertEquals(1, listMovie.page)
        assertEquals(2, listMovie.results.size)
        assertEquals(3, listMovie.totalPages)
        assertEquals(6, listMovie.totalResults)

        // Memeriksa nilai-nilai dari setiap objek Result di dalam results list
        assertEquals(1, listMovie.results[0].id)
        assertEquals("Movie 1", listMovie.results[0].title)
        assertEquals(2, listMovie.results[1].id)
        assertEquals("Movie 2", listMovie.results[1].title)
    }
}
