package com.example.groceryandrestaurantseatbooking.Authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.groceryandrestaurantseatbooking.MainActivity
import com.example.groceryandrestaurantseatbooking.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginUser:AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_user)
        val loginbutton:Button=findViewById(R.id.LoginUserButton)
        val emailEditButton:EditText=findViewById(R.id.LoginEmailEdit)
        val password:EditText=findViewById(R.id.PasswordEditText)

        loginbutton.setOnClickListener {
            if (emailEditButton.text.isEmpty()){
                Toast.makeText(this,"Please enter the email",Toast.LENGTH_LONG).show()
            }
            else if(password.text.isEmpty()){
                Toast.makeText(this,"Please enter the password",Toast.LENGTH_LONG).show()
            }
            else{
                auth=Firebase.auth
                auth.signInWithEmailAndPassword(emailEditButton.text.toString(),password.text.toString())
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                        else{
                            Toast.makeText(this,"${task.exception}",Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

    }
}