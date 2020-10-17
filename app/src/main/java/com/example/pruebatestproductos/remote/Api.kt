package com.example.pruebatestproductos.remote

import com.example.pruebatestproductos.pojo.Products
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("products")
    fun getProducts(): Call<List<Products>>
}