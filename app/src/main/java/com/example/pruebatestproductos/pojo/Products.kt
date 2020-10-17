package com.example.pruebatestproductos.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products_table")
data class Products (@PrimaryKey() @NonNull val  id : Int,
                     val name: String,
                     val price: String,
                     val image: String)
{







}