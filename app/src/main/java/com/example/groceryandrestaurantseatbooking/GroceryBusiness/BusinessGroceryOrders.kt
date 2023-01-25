package com.example.groceryandrestaurantseatbooking.GroceryBusiness

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.R
import com.example.groceryandrestaurantseatbooking.RestaurantBusiness.OrderAdapter
import com.example.groceryandrestaurantseatbooking.RestaurantBusiness.OrderDetails
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BusinessGroceryOrders : Fragment() {
    val db= Firebase.firestore
    val auth= Firebase.auth
    lateinit var orderList:ArrayList<OrderDetails>
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_business_grocery_orders, container, false)
        recyclerView=view.findViewById(R.id.RecyclerViewBusinessgroceryOrders)
        recyclerView.layoutManager= LinearLayoutManager(context)

        orderList= arrayListOf()
        val userEmail=auth.currentUser!!.email
        db.collection("Grocery").document(userEmail.toString()).collection("Orders").get().addOnSuccessListener {
            for (data in it.documents){
                if(data!=null){
                    val details= OrderDetails(data.get("UserName").toString(),data.get("ItemName").toString(),data.get("TotalAmount").toString(),data.get("TotalQuantity").toString())
                    orderList.add(details)
                }
            }
        }
        val orderAdapter= OrderAdapter(orderList)

        orderAdapter.setOnItemClick(object :OrderAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
            }

        })
        val swipeGesture=object :SwipeGesture(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction){
                    ItemTouchHelper.LEFT->{
                        orderAdapter.deleteItem(viewHolder.adapterPosition)
                        val documentSnapshotref=db.collection("Grocery").document(userEmail.toString()).collection("Orders").whereEqualTo("ItemName",orderList[viewHolder.adapterPosition].itemName).whereEqualTo("UserName",orderList[viewHolder.adapterPosition].customerName)
                        documentSnapshotref.get().addOnSuccessListener {
                            for (data in it){
                                data.reference.delete()
                            }
                        }
                    }
                        ItemTouchHelper.RIGHT->{
                           val documentSnapshotref=db.collection("Grocery").document(userEmail.toString()).collection("Orders").whereEqualTo("ItemName",orderList[viewHolder.adapterPosition].itemName).whereEqualTo("UserName",orderList[viewHolder.adapterPosition].customerName)

                            documentSnapshotref.get().addOnSuccessListener {
                                for (data in it){
                                    data.reference.delete()
                                }
                            }
                            db.collection("Grocery").document(userEmail.toString()).collection("PastOrders").document().set(orderList[viewHolder.adapterPosition]).addOnSuccessListener {
                                Toast.makeText(context,"Order accepted",Toast.LENGTH_LONG).show()

                            }
                        }
                }

            }
        }
        val itemTouchHelper=ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter=orderAdapter

        return view
    }


}