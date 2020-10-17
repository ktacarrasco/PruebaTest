package com.example.pruebatestproductos.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.pruebatestproductos.db.RoomDBProducts
import com.example.pruebatestproductos.pojo.Products

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (context: Context){

    private val tag = "Repository"

    //esto viene  de la Base de datos
    private val db: RoomDBProducts = RoomDBProducts.getDatabase(context)
    private val prodList = db.prodDao().getAllList()



    fun  passIdtoFragment(id :Int): LiveData<Products> {

        return  db.prodDao().getIdList(id)
    }

    fun passLiveDataToViewModel(): LiveData<List<Products>> {
        return prodList
    }

    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = Retrofit.retrofitInstance()
        val call = service.getProducts()


        call.enqueue(object : Callback<List<Products>> {
            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {

                    db.prodDao().insertAll(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                Log.d(tag, t.message.toString())

            }
        })

    }
}