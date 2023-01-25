package com.example.groceryandrestaurantseatbooking.RestaurantBusiness

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.Customer.CutomerRecyclerAdapter
import com.example.groceryandrestaurantseatbooking.R

class OrderAdapter(val orderList:ArrayList<OrderDetails>):RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private lateinit var mListener:OrderAdapter.onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClick(listener:OrderAdapter.onItemClickListener){
        mListener=listener
    }
    fun deleteItem(i:Int){
        orderList.removeAt(i)
        notifyDataSetChanged()
    }
    fun AddItem(i:Int,orderDetails: OrderDetails){
        orderList.add(i,orderDetails)
        notifyDataSetChanged()
    }


    class OrderViewHolder(itemView: View,listener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        val customerName:TextView=itemView.findViewById(R.id.customerNameOrderList)
        val OrderedDish:TextView=itemView.findViewById(R.id.dishNameCustomerOrder)
        val orderedPrice:TextView=itemView.findViewById(R.id.CustomerOrderPrice)
        val quantity:TextView=itemView.findViewById(R.id.customerQuantity)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.orders_item_list,parent,false)
        return OrderViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.customerName.text=orderList[position].customerName
        holder.OrderedDish.text=orderList[position].itemName
        holder.orderedPrice.text=orderList[position].price
        holder.quantity.text=orderList[position].number
    }

    override fun getItemCount(): Int {
        return orderList.size
    }


}