package com.example.groceryandrestaurantseatbooking.RestaurantBusiness

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.R

class MenuAdapter(val menuList:List<com.example.groceryandrestaurantseatbooking.RestaurantBusiness.Menu>):RecyclerView.Adapter<MenuAdapter.MenuAdapterViewHolder> (){
    class MenuAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView =itemView.findViewById(R.id.GroceryItemName)
        val desc: TextView =itemView.findViewById(R.id.GroceryItemDesc)
        val price: TextView =itemView.findViewById(R.id.GroceryItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapterViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.business_grocery_cuurent_item_list,parent,false)
        return MenuAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuAdapterViewHolder, position: Int) {
        holder.name.text=menuList[position].name
        holder.desc.text=menuList[position].Description
        holder.price.text=menuList[position].Price
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
}