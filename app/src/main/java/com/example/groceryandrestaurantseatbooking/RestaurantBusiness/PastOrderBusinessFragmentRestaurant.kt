package com.example.groceryandrestaurantseatbooking.RestaurantBusiness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.PastOrdersAdapter
import com.example.groceryandrestaurantseatbooking.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PastOrderFragmentRestaurant : Fragment() {
    val auth= Firebase.auth
    val db= Firebase.firestore
    lateinit var orderList:ArrayList<OrderDetails>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PastOrdersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_past_order_restaurant, container, false)
        recyclerView=view.findViewById(R.id.RecyclerViewBusinessRestaurantPastOrders)

        loadData()

        recyclerView.layoutManager= LinearLayoutManager(context)
        adapter= PastOrdersAdapter(orderList)

        recyclerView.adapter=adapter
        return view
    }

    private fun loadData() {
        orderList= arrayListOf()
        val userEmail=auth.currentUser!!.email
        db.collection("Restaurant").document(userEmail.toString()).collection("PastOrders").get().addOnSuccessListener {
            for(data in it.documents){
                val order=OrderDetails(data.get("customerName").toString(),data.get("itemName").toString(),data.get("number").toString(),data.get("price").toString())
                orderList.add(order)
            }
        }
    }
}