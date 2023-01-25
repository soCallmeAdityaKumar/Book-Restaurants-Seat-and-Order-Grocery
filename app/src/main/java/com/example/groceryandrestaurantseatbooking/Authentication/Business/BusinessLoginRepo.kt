package com.example.groceryandrestaurantseatbooking.Authentication.Business

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.groceryBusiness
import com.example.groceryandrestaurantseatbooking.MainActivity
import com.example.groceryandrestaurantseatbooking.RestaurantBusiness.RestaurantBusiness
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class BusinessLoginRepo {
    val auth: FirebaseAuth = Firebase.auth
    val db=Firebase.firestore

    fun signinWithEmail(email: String, password: String, context: Context) {
        if (email.isEmpty()) {
            Toast.makeText(context, "Please enter the email", Toast.LENGTH_LONG).show()
        } else if (password.isEmpty() == true) {
            Toast.makeText(context, "Please enter the password", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        Log.d("user", task.result.user!!.uid)
                       db.collection("UserInfo").document("BusinessAccounts").collection("BusinessAccounts").document(email).get().addOnSuccessListener {
                           if(it !=null){
                               val groceryValue= it.get("Grocery")
                               Log.d("user","$groceryValue")
                               val restaurantValue= it.get("Restaurant")
                               Log.d("user","$restaurantValue")
                               if(groceryValue==true){
                                   context.startActivity(Intent(context,groceryBusiness::class.java))
                               }
                               else if(restaurantValue ==true){
                                context.startActivity(Intent(context,RestaurantBusiness::class.java))

                               }
                           }

                       }
                    } else {
                        Toast.makeText(context, "${task.exception}", Toast.LENGTH_LONG).show()
                    }

                }
        }


    }
}
