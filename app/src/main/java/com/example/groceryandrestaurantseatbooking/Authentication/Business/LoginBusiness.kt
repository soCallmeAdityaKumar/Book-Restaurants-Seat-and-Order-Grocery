package com.example.groceryandrestaurantseatbooking.Authentication.Business

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.groceryandrestaurantseatbooking.R

class LoginBusiness:AppCompatActivity() {
    lateinit var viewModel: loginBusinessViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.business_login)

        val loginEmailBusinessEditText:EditText=findViewById(R.id.LoginEmailBusinessEdit)
        val loginPasswordBusiness:EditText=findViewById(R.id.LoginPasswordBusinessEdit)
        val loginBusiness:Button=findViewById(R.id.LoginBusinessButton)
        val registerBusinessTextView:TextView=findViewById(R.id.SignupBusinessTextView)

        viewModel=ViewModelProvider(this).get(loginBusinessViewModel::class.java)


        registerBusinessTextView.setOnClickListener {
            startActivity(Intent(this, BusinessRegister::class.java))
        }

        loginBusiness.setOnClickListener {
            viewModel.startLoginViewModel(loginEmailBusinessEditText.text.toString(),loginPasswordBusiness.text.toString(),this)
        }

    }
}