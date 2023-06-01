package com.example.bismillahjadi.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.bismillahjadi.FavDummy
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritDatabaseTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var database:FavoritDatabase
    private lateinit var dao: FavoritDao
    private val sampleFavorite = FavDummy.generateDummyFavourite()[0]

    @Before
    fun initDb(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoritDatabase::class.java
        ).build()
        dao = database.favoritDao()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun saveFavouriteSuccess() = runTest {
        dao.addToFavorit(sampleFavorite)
        val actualFavorite = dao.getAllMovieFavorit()
        assertEquals(sampleFavorite.title, actualFavorite[0].title)
        println(sampleFavorite.title)
        println(actualFavorite[0].title)
    }

    @Test
    fun deleteFav() = runTest {
        dao.addToFavorit(sampleFavorite)
        dao.deleteFilmFavorit(sampleFavorite)
        val actualNews = dao.getAllMovieFavorit()
        assertTrue(actualNews.isEmpty())
    }
}
