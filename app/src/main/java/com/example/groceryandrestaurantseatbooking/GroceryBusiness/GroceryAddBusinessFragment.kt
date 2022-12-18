package com.example.groceryandrestaurantseatbooking.GroceryBusiness

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
import com.example.groceryandrestaurantseatbooking.Authentication.User.User
import com.example.groceryandrestaurantseatbooking.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GroceryAddBusinessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GroceryAddBusinessFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var groceryList:ArrayList<Grocery>
    var db= Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view= inflater.inflate(R.layout.fragment_grocery_add_business, container, false)

        val floatingActionButton:FloatingActionButton=view.findViewById(R.id.AddGroceryBusinesFLoatingButton)
        floatingActionButton.setOnClickListener {
            Log.d("floating","Inside the floating action button")
            context?.startActivity(Intent(context,AddGroceryToBusiness::class.java))
        }

        groceryList= arrayListOf()

        recyclerView=view.findViewById(R.id.BusinessGroceryAddRecylerView)
        recyclerView.layoutManager=LinearLayoutManager(context)
        db=FirebaseFirestore.getInstance()

        val userId=Firebase.auth.currentUser!!.uid
        db.collection(userId).get().addOnSuccessListener {
            if(!it.isEmpty){
                for(data in it.documents){
                    Log.d("data","${data.get("Itemname")}")
                    val grocery=Grocery(data.get("Itemname").toString(),data.get("Itemdesc").toString(),data.get("Itemprice").toString())
                    if(grocery!=null){
                        groceryList.add(grocery)
                    }
//                    if(grocery!=null){
//                        groceryList.add(grocery)
//                    }
                }
                recyclerView.adapter=GroceryAdapter(groceryList)
            }
        }
            .addOnFailureListener {

                Toast.makeText(context,"Failed to load Business Grocery list",Toast.LENGTH_LONG).show()
            }



        return view
    }

}