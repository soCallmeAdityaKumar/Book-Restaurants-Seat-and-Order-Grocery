package com.example.groceryandrestaurantseatbooking.Authentication.User

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.groceryandrestaurantseatbooking.Authentication.User.LoginRepo

class LoginViewModel:ViewModel() {
     var repo= LoginRepo()

    fun startLoginWithEmail( email: String, password: String, context: Context){
        repo.signinWithEmail(email,password,context)
    }

}