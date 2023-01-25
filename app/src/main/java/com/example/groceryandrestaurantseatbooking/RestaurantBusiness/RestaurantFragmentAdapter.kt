package com.example.groceryandrestaurantseatbooking.RestaurantBusiness

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.BusinessGroceryOrders
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.GroceryAddBusinessFragment
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.PastOrdersBusinessGroceryFragment

class RestaurantFragmentAdapter(supportFragmentManager: FragmentManager, lifecycle: Lifecycle,val  tabCount: Int):FragmentStateAdapter(supportFragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0->{return MenuAddFragmentBusiness()
            }
            1-> { return BusinessRestaurantOrders()
            }
            2->{return PastOrdersBusinessGroceryFragment()
            }
            else->{return MenuAddFragmentBusiness()
            }
        }
    }

}
