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

class RegisterBusinessRepo {
    val auth:FirebaseAuth= Firebase.auth
    val db=Firebase.firestore
    val email=auth.currentUser!!.email

    var grocery:Boolean=false
    set(value) {
        field =value
    }

    var restaurant:Boolean=false
    set(value) {
        field=value
    }

    fun startBusinessRegistration(business: Business, context:Context){
        if(business.name.isEmpty()){
            Toast.makeText(context,"Please enter the email", Toast.LENGTH_LONG).show()
        }
        else if(business.email.isEmpty()){
            Toast.makeText(context,"Please enter the password", Toast.LENGTH_LONG).show()
        }
        else if(business.phoneNumber.isEmpty()){
            Toast.makeText(context,"Please enter the name", Toast.LENGTH_LONG).show()
        }
        else if(business.password.isEmpty()){
            Toast.makeText(context,"Please enter the phone number", Toast.LENGTH_LONG).show()
        }
        else{
            auth.createUserWithEmailAndPassword(business.email,business.password).addOnCompleteListener {task->
                if(task.isSuccessful){
                    val userUid=task.result.user!!.uid
                    val userHash= hashMapOf(
                        "BusinessEmail" to email,
                        "Grocery" to grocery,
                        "Restaurant" to restaurant,
                        "Uid" to userUid
                    )
                    Log.d("Task register business","task is successfull $grocery and $restaurant")
                    if(grocery){
                        db.collection("UserInfo").document("BusinessAccounts").collection("BusinessAccounts")
                            .document(email.toString()).set(userHash).addOnSuccessListener {
                            Log.d("user","User added to firebase")
                        }.addOnFailureListener {
                            Log.d("user","Failed to add user to firebase")
                        }
                       context.startActivity(Intent(context,groceryBusiness::class.java))
                    }
                    else if(restaurant){
                        db.collection("UserInfo").document("BusinessAccounts").collection("BusinessAccounts").document(email.toString()).set(userHash).addOnSuccessListener {
                            Log.d("user","User added to firebase")
                        }.addOnFailureListener {
                            Log.d("user","Failed to add user to firebase")
                        }
                        context.startActivity(Intent(context,RestaurantBusiness::class.java))
                    }
                }
                else{
                    Toast.makeText(context,"Unable to register user", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}