package com.example.groceryandrestaurantseatbooking.GroceryBusiness

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class GroceryFragmentAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle,var tabCount:Int):FragmentStateAdapter(fragmentManager,lifecycle) {


    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0->{return GroceryAddBusinessFragment()}
            1->{return BusinessGroceryOrders()}
            2->{return PastOrdersBusinessGroceryFragment()}
            else->{return GroceryAddBusinessFragment()}
        }
    }


}