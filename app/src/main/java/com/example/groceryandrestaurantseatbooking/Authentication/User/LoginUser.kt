package com.example.groceryandrestaurantseatbooking.Authentication.User

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.groceryandrestaurantseatbooking.R

class LoginUser:AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_user)
         val loginbutton:Button=findViewById(R.id.LoginUserButton)
         val emailEditButton:EditText=findViewById(R.id.LoginEmailEdit)
         val password:EditText=findViewById(R.id.LoginPasswordEdit)
         val signupText:TextView=findViewById(R.id.SignupTextView)

        val business:TextView=findViewById(R.id.BusinessLoginText)


         loginViewModel=ViewModelProvider(this).get(LoginViewModel::class.java)

        signupText.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        loginbutton.setOnClickListener {
            loginViewModel.startLoginWithEmail(emailEditButton.text.toString(),password.text.toString(),this)
        }

        business.setOnClickListener {

        }

    }
}