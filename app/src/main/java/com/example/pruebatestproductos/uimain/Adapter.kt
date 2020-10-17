package com.example.pruebatestproductos.uimain

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatestproductos.R
import com.example.pruebatestproductos.pojo.Products
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_products.view.*

class Adapter(var mdataSetP: List<Products>, var listenerS: MyClickListener) : RecyclerView.Adapter<Adapter.prodHolder>()
{
    fun updateData(list: List<Products>) {
        Log.d("UPDATE", "update ${list.size}")
        mdataSetP = list
        notifyDataSetChanged()
    }

    class prodHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTv= itemView.titleTV
        val photoTv= itemView.photoTV

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): prodHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)

        return prodHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("Cantidad",mdataSetP.size.toString())
        return mdataSetP.size
    }

    override fun onBindViewHolder(holder: prodHolder, position: Int) {
        val photo =  mdataSetP[position]

        val titletv ="Super Hero: ${photo.name}"
        //val phototv="images: ${Images.sm}"

        holder.titleTv.text = titletv

        Picasso.get()
            .load(photo.image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.photoTv)

        holder.itemView.setOnClickListener(View.OnClickListener{

            Toast.makeText(holder.itemView.context,"$titletv", Toast.LENGTH_SHORT).show()
            listenerS.onItemClick(mdataSetP.get(position))

        })
    }

    interface MyClickListener {

        fun onItemClick(products: Products)
    }


}