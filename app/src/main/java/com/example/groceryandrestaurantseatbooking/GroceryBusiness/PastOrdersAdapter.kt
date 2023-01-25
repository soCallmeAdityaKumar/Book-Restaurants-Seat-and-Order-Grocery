package com.example.groceryandrestaurantseatbooking.GroceryBusiness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.R
import com.example.groceryandrestaurantseatbooking.RestaurantBusiness.OrderDetails

class PastOrdersAdapter(val orderList:ArrayList<OrderDetails>):RecyclerView.Adapter<PastOrdersAdapter.viewHolder>() {
    class viewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val customer:TextView=itemView.findViewById(R.id.customerNameOrderList)
        val itemDish:TextView=itemView.findViewById(R.id.dishNameCustomerOrder)
        val price:TextView=itemView.findViewById(R.id.CustomerOrderPrice)
        val quantity:TextView=itemView.findViewById(R.id.customerQuantity)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.orders_item_list,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.itemDish.text=orderList[position].itemName
        holder.customer.text=orderList[position].customerName
        holder.price.text=orderList[position].price
        holder.quantity.text=orderList[position].price
    }

    override fun getItemCount(): Int {
        return orderList.size

    }
}