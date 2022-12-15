package com.example.groceryandrestaurantseatbooking.Authentication

import android.content.Context
import androidx.lifecycle.ViewModel

class loginBusinessViewModel: ViewModel() {
    val repo=BusinessLoginRepo()
    fun startLoginViewModel(email:String,password:String,context: Context){
        repo.signinWithEmail(email, password, context)
    }
}