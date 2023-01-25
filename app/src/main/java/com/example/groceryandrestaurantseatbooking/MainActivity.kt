package com.example.groceryandrestaurantseatbooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.groceryandrestaurantseatbooking.Customer.GroceryFragment
import com.example.groceryandrestaurantseatbooking.Customer.RestaurantFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(GroceryFragment())

        val bottomNavigationView:BottomNavigationView=findViewById(R.id.bottomNavigationView)
       bottomNavigationView.setOnItemSelectedListener {
           when (it.itemId)
           {
               R.id.GroceryID -> replaceFragment(GroceryFragment())
               R.id.RestaurantsID -> replaceFragment(RestaurantFragment())
               else->{
               }
           }
        true

    }
    }

    public fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransac=fragmentManager.beginTransaction()
        fragmentTransac.replace(R.id.FrameLayout,fragment)
        fragmentTransac.commit()
    }
}