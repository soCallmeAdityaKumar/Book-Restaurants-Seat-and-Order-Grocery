package com.example.groceryandrestaurantseatbooking.Authentication.Business

import android.content.Context
import androidx.lifecycle.ViewModel

class BusinessRegisterViewModel:ViewModel() {
    val repo= RegisterBusinessRepo()
    var grocery:Boolean=false
    set(value) {
        field=value
    }
    var restaurant:Boolean=false
    set(value) {
       field=value
    }

    fun startBusinessRegisViewModel(business: Business, context: Context){
        repo.startBusinessRegistration(business,context)
        repo.grocery=grocery
        repo.restaurant=restaurant
    }
}