package com.example.pruebatestproductos.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebatestproductos.pojo.Products

@Dao
interface DaoProducts {

    //Insertar un listado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listProducts: List<Products>)

    // Insertar 1 post
    /* @Insert
     suspend fun insertPost(post: Post)*/

    // traer todos los elementos de la tabla
    @Query("SELECT * FROM Products_table ")
    fun getAllList() : LiveData<List<Products>>

    //traer elemento desde id
    @Query("SELECT * FROM Products_table WHERE id=:mId")
    fun getIdList(mId:Int) : LiveData<Products>



}