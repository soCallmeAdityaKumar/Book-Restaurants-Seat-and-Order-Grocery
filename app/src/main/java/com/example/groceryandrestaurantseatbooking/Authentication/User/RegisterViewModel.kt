package com.example.groceryandrestaurantseatbooking.Authentication.User

import android.content.Context
import androidx.lifecycle.ViewModel

class RegisterViewModel:ViewModel() {
    val registerRepo= RegisterRepo()
    fun registerViewModel(user: User, context: Context){
        registerRepo.startRegistration(user,context)
    }
}