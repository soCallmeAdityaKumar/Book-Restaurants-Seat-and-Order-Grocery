package com.example.groceryandrestaurantseatbooking.Customer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.Grocery
import com.example.groceryandrestaurantseatbooking.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class GroceryFragment : Fragment(){
    lateinit var recyclerView:RecyclerView
    lateinit var groceryList:ArrayList<Grocery>
    lateinit var groceryEmail:ArrayList<String>
    val db=Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.grocery_customer, container, false)
        recyclerView=view!!.findViewById(R.id.recyclerViewGroceryCustomer)
        recyclerView.layoutManager= LinearLayoutManager(context)
        loadData()

        return  view
    }

    private fun displayGroceryData() {
        Log.d("position","Inside display Grocery Data")
        groceryList= arrayListOf()
        for(emaildata in groceryEmail){
            Log.d("data","email is from $emaildata")
            db.collection("Grocery").document(emaildata).collection("CurrentStocks")
                .get().addOnSuccessListener {
                    for(data in it.documents){
                        Log.d("data","${data.get("Itemname")}")
                        val grocery=Grocery(data.get("ItemName").toString(),data.get("ItemDesc").toString(),data.get("ItemPrice").toString(),data.get("BusinessEmail").toString())
                        groceryList.add(grocery)
                    }

            }.addOnFailureListener {
                    Log.d("Failed","Failed to load data from ")
                }

        }
        Log.d("position","Outside of for loop of displayGroceryData")
        val cutomerRecyclerAdapter=CutomerRecyclerAdapter(groceryList)
        recyclerView.adapter=cutomerRecyclerAdapter
        cutomerRecyclerAdapter.setOnItemClickListener(object :CutomerRecyclerAdapter.onItemClickListener{
            override fun onitemClick(position: Int) {
                val intent=Intent(context,BookGroceryCustomer::class.java)
                intent.putExtra("grocery",groceryList[position])
                context!!.startActivity(intent)
            }

        })

    }

    private fun loadData() {

        groceryEmail= arrayListOf()
        db.collection("UserInfo").document("BusinessAccounts").collection("BusinessAccounts")
            .get().addOnSuccessListener {
            for(data in it.documents){
                if(data.get("Grocery") == true){
                    Log.d("email","email is ${data.get("BusinessEmail")}")
                    groceryEmail.add(data.get("BusinessEmail").toString())
                }
            }

            Log.d("position","Outside of for loop of loadData")
            displayGroceryData()

        }.addOnFailureListener {
            Log.d("Customer","failed to  Load Data to customer")
        }
    }
}
