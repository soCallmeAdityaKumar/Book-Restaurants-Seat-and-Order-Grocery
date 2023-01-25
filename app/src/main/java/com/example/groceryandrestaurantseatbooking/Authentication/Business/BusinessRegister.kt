package com.example.groceryandrestaurantseatbooking.Authentication.Business

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.groceryandrestaurantseatbooking.R

class BusinessRegister:AppCompatActivity() {
    lateinit var viewModel: BusinessRegisterViewModel
    var grocery:Boolean=false
    var restaurant:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_business)
        val businessName:EditText=findViewById(R.id.NameBusinessEditText)
        val businessEmail:EditText=findViewById(R.id.EmailBusinessEditText)
        val BusinessphoneNumber:EditText=findViewById(R.id.PhoneNumberBusinessEditText)
        val passwordBusiness:EditText=findViewById(R.id.PasswordBusinessEditText)
        val loginBusinessTextView:TextView=findViewById(R.id.LoginBusinessTextView)
        val registerButton: Button =findViewById(R.id.RegisterBusinessbutton)
        val groceryRadioButton:RadioButton=findViewById(R.id.GroceryradioButton)
        val restaurantRadioButton:RadioButton=findViewById(R.id.RestaurantsRadioButton)
        val radioGroup:RadioGroup=findViewById(R.id.RadioGroup)

        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            val radioButton=findViewById<RadioButton>(id)
           when(radioButton){
               groceryRadioButton->{grocery=true}
               restaurantRadioButton->{restaurant=true}
           }

            Log.d("radiogroup","$grocery and  $restaurant")
            viewModel.grocery=grocery
            viewModel.restaurant=restaurant
        }

        viewModel=ViewModelProvider(this).get(BusinessRegisterViewModel::class.java)

        loginBusinessTextView.setOnClickListener {
            startActivity(Intent(this, LoginBusiness::class.java))
        }
        registerButton.setOnClickListener {
            val business= Business(businessName.text.toString(),businessEmail.text.toString(),BusinessphoneNumber.text.toString(),passwordBusiness.text.toString())
            viewModel.startBusinessRegisViewModel(business,this)
        }



    }
}