package com.example.groceryandrestaurantseatbooking.RestaurantBusiness

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.AddGroceryToBusiness
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.Grocery
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.GroceryAdapter
import com.example.groceryandrestaurantseatbooking.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MenuAddFragmentBusiness : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var menuList:ArrayList<Menu>
    var db= Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_menu_add_business, container, false)


        val floatingActionButton: FloatingActionButton =view.findViewById(R.id.AddMenuBusinesFLoatingButton)
        floatingActionButton.setOnClickListener {
            Log.d("floating","Inside the floating action button")
            context?.startActivity(Intent(context, AddMenuToBusiness::class.java))
        }

        menuList= arrayListOf()

        recyclerView=view.findViewById(R.id.BusinessMenuAddRecylerView)
        recyclerView.layoutManager= LinearLayoutManager(context)
        db= FirebaseFirestore.getInstance()

        val userEmail=Firebase.auth.currentUser!!.email
        db.collection("Restaurant").document(userEmail.toString()).collection("CurrentMenu")
            .get().addOnSuccessListener {
            if(!it.isEmpty){
                for(data in it.documents){
                    Log.d("data","${data.get("Itemname")}")
                    val menu=Menu(data.get("Itemname").toString(),data.get("Itemdesc").toString(),data.get("Itemprice").toString(),data.get("Email").toString())
                    if(menu!=null){
                        menuList.add(menu)
                    }
                }
                recyclerView.adapter= MenuAdapter(menuList)
            }
        }
            .addOnFailureListener {

                Toast.makeText(context,"Failed to load Dish Menu list", Toast.LENGTH_LONG).show()
            }


        return view
    }
}