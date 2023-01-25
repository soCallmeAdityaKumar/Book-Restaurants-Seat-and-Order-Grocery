package com.example.groceryandrestaurantseatbooking.GroceryBusiness

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.R
import org.w3c.dom.Text
import java.util.zip.Inflater

class GroceryAdapter(val groceryList:ArrayList<Grocery>):RecyclerView.Adapter<GroceryAdapter.GroceryAdapterViewHolder>() {
    class GroceryAdapterViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val name: TextView =itemView.findViewById(R.id.GroceryItemName)
        val desc:TextView=itemView.findViewById(R.id.GroceryItemDesc)
        val price:TextView=itemView.findViewById(R.id.GroceryItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryAdapterViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.business_grocery_cuurent_item_list,parent,false)
        return GroceryAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryAdapterViewHolder, position: Int) {
        holder.name.text=groceryList[position].name
        holder.desc.text=groceryList[position].Description
        holder.price.text=groceryList[position].Price
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }
}