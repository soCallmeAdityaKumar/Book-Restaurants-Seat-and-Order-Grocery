package com.example.groceryandrestaurantseatbooking.Authentication.Business

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.groceryandrestaurantseatbooking.Authentication.Business.BusinessLoginRepo

class loginBusinessViewModel: ViewModel() {
    val repo= BusinessLoginRepo()
    fun startLoginViewModel(email:String,password:String,context: Context){
        repo.signinWithEmail(email, password, context)
    }
}