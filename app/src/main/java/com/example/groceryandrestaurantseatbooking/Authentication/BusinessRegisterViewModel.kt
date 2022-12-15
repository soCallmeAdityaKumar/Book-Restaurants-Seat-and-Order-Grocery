package com.example.groceryandrestaurantseatbooking.Authentication

import android.content.Context
import androidx.lifecycle.ViewModel

class BusinessRegisterViewModel:ViewModel() {
    val repo=RegisterBusinessRepo()
    fun startBusinessRegisViewModel(business:Business,context: Context){
        repo.startBusinessRegistration(business,context)
    }
}