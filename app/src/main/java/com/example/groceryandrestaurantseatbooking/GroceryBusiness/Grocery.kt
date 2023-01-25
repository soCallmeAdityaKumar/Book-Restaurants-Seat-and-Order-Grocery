package com.example.groceryandrestaurantseatbooking.GroceryBusiness

import android.os.Parcel
import android.os.Parcelable

data class Grocery(val name:String,val Description:String,val Price:String,val email:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(Description)
        parcel.writeString(Price)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Grocery> {
        override fun createFromParcel(parcel: Parcel): Grocery {
            return Grocery(parcel)
        }

        override fun newArray(size: Int): Array<Grocery?> {
            return arrayOfNulls(size)
        }
    }
}
