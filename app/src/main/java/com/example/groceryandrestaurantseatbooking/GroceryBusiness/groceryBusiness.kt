package com.example.groceryandrestaurantseatbooking.GroceryBusiness

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.groceryandrestaurantseatbooking.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class groceryBusiness:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.business)

        val viewPager2=findViewById<ViewPager2>(R.id.groceryViewPager)

        val tablayout:TabLayout=findViewById(R.id.GrocerytabLayout)

        tablayout.addTab(tablayout.newTab().setText("Grocery").setIcon(R.drawable.ic_baseline_add_business_24))
        tablayout.addTab(tablayout.newTab().setText("Orders").setIcon(R.drawable.ic_baseline_add_shopping_cart_24))
        tablayout.addTab(tablayout.newTab().setText("Past Orders").setIcon(R.drawable.ic_baseline_shopping_cart_24))

        val adapter=GroceryFragmentAdapter(supportFragmentManager,lifecycle,tablayout.tabCount)
        viewPager2.adapter=adapter


        tablayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2.currentItem=tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }
}

