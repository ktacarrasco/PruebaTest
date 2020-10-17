package com.example.pruebatestproductos.uimain

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatestproductos.R
import com.example.pruebatestproductos.pojo.Products
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() , Adapter.MyClickListener{
    private var prodList =  ArrayList<Products>()

    private lateinit var viewAdapter: Adapter
    private lateinit var mViewModel: MainViewModel
    private lateinit var mFragment: FirstFragment


    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view :View=inflater.inflate(R.layout.fragment_first,container,false)


        return view




    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Iniciando el ViewModel
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Iniciando el adapter
        viewAdapter = Adapter(prodList,this)
        shRecyclerView.layoutManager = LinearLayoutManager(context)
        shRecyclerView.adapter = viewAdapter

        mViewModel.fetchFromServer()
        mViewModel.getDataFromDB(id).observe(viewLifecycleOwner, Observer {
            Log.d("cant", it.toString())
            viewAdapter.updateData(it)


        })
    }

    override fun onItemClick(products: Products) {

        val bundle=Bundle()
        bundle.putInt("id",products.id)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)



    }

}