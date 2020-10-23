package com.example.pruebatestproductos.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

import com.example.pruebatestproductos.db.DaoProducts
import com.example.pruebatestproductos.db.RoomDBProducts
import com.example.pruebatestproductos.pojo.Products
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var daoProducts: DaoProducts
    private lateinit var db: RoomDBProducts

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RoomDBProducts::class.java).build()
        daoProducts = db.prodDao() // RoomDBProducts
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertListElements() = runBlocking {
        //given
        val drugStoreList = listOf(Products( 8 ,  "Nokia 2.3 32GB",
            "95760",
            "https://images.ctfassets.net/wcfotm6rrl7u/7BhaseqEIFMGpIi3jVzA6P/e9b5ed6e0896f9883ca0190e4a8ab697/1-default-Nokia_2_3-sand.png?fm=webp&q=65&w=1200",
            "Tamaño 6,2''\nDensidad 271 ppi\nResolución de pantalla 1520 x 720 pixeles",
            99760,
            true))

        // when
        daoProducts.insertAll(drugStoreList)

        //then
        daoProducts.getAllList().observeForever{

            assertThat(it).isNotNull()
            println(it.toString())
            assertThat(it[0].name).isEqualTo("Nokia 2.3 32GB")

        }
    }

}