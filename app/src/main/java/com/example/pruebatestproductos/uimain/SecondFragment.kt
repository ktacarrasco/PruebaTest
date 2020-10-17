package com.example.pruebatestproductos.uimain

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatestproductos.R
import com.example.pruebatestproductos.pojo.Products
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.view.*
import kotlinx.android.synthetic.main.item_products_details.view.titleTV

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"
private const val ARG_PARAM2 = "name"
private const val ARG_PARAM3 = "image"


private var shListItem :  List<Products> = ArrayList<Products>()

private lateinit var viewAdapter: Adapter
private lateinit var mViewModel: MainViewModel
private lateinit var mFragment: SecondFragment
private lateinit var RecyclerView: RecyclerView
private var textdetail: TextView? = null
private var imagedetail: ImageView? = null


class SecondFragment : Fragment() , Adapter.IAdapter {

    private var param1: Int? = null
    private var param2: String? = null
    private var param3: String? = null

    // private const val ARG_PARAM2 = "images"

    private lateinit var mViewModel: MainViewModel
    private lateinit var mFragment: SecondFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)

            //Iniciando el ViewModel
            mViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        }
    }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view :View=inflater.inflate(R.layout.fragment_second, container, false)


        param1?.let {
            mViewModel.getIdDataFromDB(it).observe(viewLifecycleOwner, Observer { it ->
                Log.d("cant", it.toString())

                //viewAdapter.updateData(it)

                view.titleTV.text = " ${it.name}"
                //view.titleTV.text = it.name
                if(it.details?.description != null){
                    Log.d("description",it.details.description)
                    view.apparanceTV.text = "Description: ${it.details!!.description}"}

                view.apparance2TV.text = "Price ${it.details!!.credit}"
                view.apparance3TV.text = "Last Price ${it.details!!.lastPrice}"
                Picasso.get()
                    .load(it.image)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(view.photoTV)

            })
        }


        return view
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {

                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun getFromAdapter(id:  Int) {
        mViewModel.getDataFromDB(id)
    }


}
