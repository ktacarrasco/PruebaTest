package com.example.pruebatestproductos.remote

import com.example.pruebatestproductos.pojo.Details
import com.example.pruebatestproductos.pojo.Products
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("products")
    fun getDetails(): Call<List<Products>>

    @GET("details")
    fun getProducts(): Call<List<Products>>
}