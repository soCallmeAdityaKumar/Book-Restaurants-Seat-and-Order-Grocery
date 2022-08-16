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
import java.io.DataOutputStream
import java.util.jar.Attributes

class Register:AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_register)

        auth= Firebase.auth

        val emailEdit:EditText=findViewById(R.id.EmailEditText)
        val phoneEdit:EditText=findViewById(R.id.PhoneNumberEditText)
        val passwordEdit:EditText=findViewById(R.id.PasswordEditText)
        val nameEdit:EditText=findViewById(R.id.NameEditText)
        val registerButton=findViewById<Button>(R.id.Registerbutton)

        registerButton.setOnClickListener {
            if(emailEdit.text.isEmpty()){
                Toast.makeText(this,"Please enter the email",Toast.LENGTH_LONG).show()
            }
            else if(passwordEdit.text.isEmpty()){
                Toast.makeText(this,"Please enter the password",Toast.LENGTH_LONG).show()
            }
            else if(nameEdit.text.isEmpty()){
                Toast.makeText(this,"Please enter the name",Toast.LENGTH_LONG).show()
            }
            else if(phoneEdit.text.isEmpty()){
                Toast.makeText(this,"Please enter the phone number",Toast.LENGTH_LONG).show()
            }
            else{
                val email=emailEdit.text
                val password=passwordEdit.text
                auth.createUserWithEmailAndPassword(email.toString(),password.toString())
                    .addOnCompleteListener(this){task->
                        if(task.isSuccessful){
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                        else{
                            Toast.makeText(this,"Unable to register user",Toast.LENGTH_LONG).show()
                        }
                    }
            }

        }



    }
}