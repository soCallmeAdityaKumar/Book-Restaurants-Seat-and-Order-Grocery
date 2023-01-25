package com.example.groceryandrestaurantseatbooking.Customer

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.Grocery
import com.example.groceryandrestaurantseatbooking.R
import kotlinx.coroutines.selects.whileSelect

class CutomerRecyclerAdapter( val itemList: ArrayList<Grocery>):RecyclerView.Adapter<CutomerRecyclerAdapter.CustomerViewHolder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener {
        fun onitemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }
    class CustomerViewHolder(itemView: View,listener: onItemClickListener) :RecyclerView.ViewHolder(itemView){
        val name: TextView =itemView.findViewById(R.id.GroceryItemName)
        val desc: TextView =itemView.findViewById(R.id.GroceryItemDesc)
        val price: TextView =itemView.findViewById(R.id.GroceryItemPrice)

        init {
            itemView.setOnClickListener {
                listener.onitemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.business_grocery_cuurent_item_list,parent,false)
        return CustomerViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.name.text=itemList[position].name
        holder.desc.text=itemList[position].Description
        holder.price.text=itemList[position].Price

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}