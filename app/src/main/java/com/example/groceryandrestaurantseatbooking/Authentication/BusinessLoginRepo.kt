package com.example.groceryandrestaurantseatbooking.Authentication

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.groceryandrestaurantseatbooking.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class BusinessLoginRepo {
    val auth: FirebaseAuth = Firebase.auth

    fun signinWithEmail(email: String, password: String, context: Context) {
        if (email.isEmpty()) {
            Toast.makeText(context, "Please enter the email", Toast.LENGTH_LONG).show()
        } else if (password.isEmpty() == true) {
            Toast.makeText(context, "Please enter the password", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    } else {
                        Toast.makeText(context, "${task.exception}", Toast.LENGTH_LONG).show()
                    }

                }
        }


    }
}
