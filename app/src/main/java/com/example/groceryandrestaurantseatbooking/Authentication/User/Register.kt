package com.example.groceryandrestaurantseatbooking.Authentication.User

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.groceryandrestaurantseatbooking.R

class Register:AppCompatActivity() {
    lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_register)
        registerViewModel=ViewModelProvider(this).get(RegisterViewModel::class.java)

        val emailEdit:EditText=findViewById(R.id.EmailEditText)
        val phoneEdit:EditText=findViewById(R.id.PhoneNumberEditText)
        val passwordEdit:EditText=findViewById(R.id.PasswordEditText)
        val nameEdit:EditText=findViewById(R.id.NameEditText)
        val registerButton=findViewById<Button>(R.id.Registerbutton)
        val loginTextView:TextView=findViewById(R.id.LoginTextView)

        loginTextView.setOnClickListener {
            startActivity(Intent(this, LoginUser::class.java))
        }
        registerButton.setOnClickListener {
            val user = User(
                nameEdit.text.toString(),
                emailEdit.text.toString(),
                phoneEdit.text.toString(),
                passwordEdit.text.toString()
            )
            registerViewModel.registerViewModel(user, this)

        }
    }
}