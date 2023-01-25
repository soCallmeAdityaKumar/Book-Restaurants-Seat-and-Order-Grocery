package com.example.groceryandrestaurantseatbooking.GroceryBusiness

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.groceryandrestaurantseatbooking.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class AddGroceryToBusiness:AppCompatActivity() {
    val db=Firebase.firestore
    lateinit var itemName:EditText
    lateinit var  itemDesc:EditText
    lateinit var  itemPrice:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_groceryto_business)

        itemName=findViewById(R.id.itemNameEditText)
       itemDesc=findViewById(R.id.ItemDescEditText)
        itemPrice=findViewById(R.id.ItemPriceEditText)
        val submit:Button=findViewById(R.id.submitGrocerytoBusinessButton)

        submit.setOnClickListener {
            uploaddataToFirebase()
            Log.d("floating","Inside the Add grocery Class")
        }
    }


    private fun uploaddataToFirebase() {
        val userEmail= Firebase.auth.currentUser!!.email
        val userMap= hashMapOf(
            "ItemName" to itemName.text.toString(),
            "ItemDesc" to itemDesc.text.toString(),
            "ItemPrice" to itemPrice.text.toString(),
            "BusinessEmail" to userEmail
        )
        db.collection("Grocery").document(userEmail.toString()).collection("CurrentStocks").document()
            .set(userMap).addOnSuccessListener {
                itemName.text.clear()
                itemDesc.text.clear()
                itemPrice.text.clear()
                startActivity(Intent(this,groceryBusiness::class.java))
        }.addOnFailureListener {
            Toast.makeText(this,"Failed to Add",Toast.LENGTH_LONG).show()
        }
    }

}