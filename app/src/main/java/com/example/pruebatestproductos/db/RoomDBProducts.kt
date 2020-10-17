package com.example.pruebatestproductos.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebatestproductos.pojo.Products

@Database(entities = [Products::class], version = 1,exportSchema = false)


    abstract class RoomDBProducts : RoomDatabase(){


        abstract fun prodDao() : DaoProducts

        companion object {
            @Volatile
            private var INSTANCE: RoomDBProducts? = null

            fun getDatabase(context: Context): RoomDBProducts {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDBProducts::class.java,
                        "Prod_database")
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
}