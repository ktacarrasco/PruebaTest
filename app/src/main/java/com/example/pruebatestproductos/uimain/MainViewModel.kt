package com.example.pruebatestproductos.uimain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.pruebatestproductos.pojo.Products
import com.example.pruebatestproductos.remote.Repository

class MainViewModel  (application: Application) : AndroidViewModel(application) {
    private val repository =  Repository(application)
    private val prodList = repository.passLiveDataToViewModel()

    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    fun getDataFromDB(id: Int): LiveData<List<Products>> {
        return prodList
    }

    fun getIdDataFromDB(id: Int): LiveData<Products> {
        return repository.passIdtoFragment(id)
    }
}