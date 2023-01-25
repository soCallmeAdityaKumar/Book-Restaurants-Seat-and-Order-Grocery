package com.example.groceryandrestaurantseatbooking.Customer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.Grocery
import com.example.groceryandrestaurantseatbooking.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RestaurantFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var menuList:ArrayList<Grocery>
    lateinit var restaurantEmail:ArrayList<String>
    val db= Firebase.firestore
    private fun loadData() {
        restaurantEmail= arrayListOf()
        db.collection("UserInfo").document("BusinessAccounts").collection("BusinessAccounts").get().addOnSuccessListener {
            for(data in it.documents){
                if(data.get("Restaurant") == true){
                    Log.d("Email","email is ${data.get("BusinessEmail")}")
                    restaurantEmail.add(data.get("BusinessEmail").toString())
                }
            }

            Log.d("position","Outside of for loop of loadData")
            displayMenuData()

        }.addOnFailureListener {
            Log.d("Customer","failed to  Load Data to customer")
        }
    }

    private fun displayMenuData() {
        Log.d("position","Inside display Grocery Data")
        menuList= arrayListOf()
        for(data in restaurantEmail){
            Log.d("data","email is from $data")
            db.collection("Restaurant").document(data).collection("CurrentMenu").get().addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        Log.d("data","${data.get("ItemName")}")
                        val grocery=Grocery(data.get("ItemName").toString(),data.get("ItemDesc").toString(),data.get("ItemPrice").toString(),data.get("BusinessEmail").toString())
                        menuList.add(grocery)
                    }
                }
            }.addOnFailureListener {
                Log.d("Failed","Failed to load data from ")
            }

        }
        Log.d("position","Outside of for loop of displayGroceryData")
        val cutomerRecyclerAdapter=CutomerRecyclerAdapter(menuList)
        recyclerView.adapter=cutomerRecyclerAdapter
        cutomerRecyclerAdapter.setOnItemClickListener(object :CutomerRecyclerAdapter.onItemClickListener{
            override fun onitemClick(position: Int) {
                val intent= Intent(context,BookyourSeat::class.java)
                intent.putExtra("menu",menuList[position])
                context!!.startActivity(intent)
            }

        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_restaurant, container, false)

        recyclerView=view!!.findViewById(R.id.recyclerViewRestaurantCustomer)
        recyclerView.layoutManager= LinearLayoutManager(context)
        loadData()
        return view
    }


}