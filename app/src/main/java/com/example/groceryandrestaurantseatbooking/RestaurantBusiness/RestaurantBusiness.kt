package com.example.groceryandrestaurantseatbooking.RestaurantBusiness

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.GroceryFragmentAdapter
import com.example.groceryandrestaurantseatbooking.R
import com.google.android.material.tabs.TabLayout

class RestaurantBusiness:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_business)

        val viewPager2=findViewById<ViewPager2>(R.id.restaurantViewPager)

        val tablayout: TabLayout =findViewById(R.id.RestauranttabLayout)

        tablayout.addTab(tablayout.newTab().setText("Menu").setIcon(R.drawable.ic_baseline_dining_24))
        tablayout.addTab(tablayout.newTab().setText("Orders").setIcon(R.drawable.ic_baseline_add_shopping_cart_24))
        tablayout.addTab(tablayout.newTab().setText("Past Orders").setIcon(R.drawable.ic_baseline_shopping_cart_24))

        val adapter= RestaurantFragmentAdapter(supportFragmentManager,lifecycle,tablayout.tabCount)
        viewPager2.adapter=adapter


        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2.currentItem=tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}