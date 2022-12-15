package com.example.groceryandrestaurantseatbooking.Authentication

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.groceryandrestaurantseatbooking.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterBusinessRepo {
    val auth:FirebaseAuth= Firebase.auth
    fun startBusinessRegistration(business:Business,context:Context){
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
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
                else{
                    Toast.makeText(context,"Unable to register user", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}