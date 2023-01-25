package com.example.groceryandrestaurantseatbooking.Customer

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.Grocery
import com.example.groceryandrestaurantseatbooking.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BookyourSeat:AppCompatActivity() {
     var auth=Firebase.auth
     var db=Firebase.firestore
    lateinit var dishName:TextView
    lateinit var dishPrice:TextView
    lateinit var totalSeats:TextView
    lateinit var menu:Grocery
    lateinit var email:String
    lateinit var bookSeat:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_seat)
        dishName=findViewById(R.id.itemDishNameOrder)
        dishPrice=findViewById(R.id.dishPriceOrder)
        totalSeats=findViewById(R.id.EnterSeatOrder)
        bookSeat=findViewById(R.id.PlaceOrderRestaurant)

        addSeats()
        menu= intent.getParcelableExtra("menu")!!

        dishName.text=menu.name
        dishPrice.text=menu.Price
        email=menu.email
        Log.d("menu","email of the businessman-> $email")

        bookSeat.setOnClickListener {
            OrderProgress()
        }


    }

    private fun OrderProgress() {
        val userEmail=auth.currentUser!!.email

        Log.d("usersdetails","email-> $userEmail")


        db.collection("UserInfo").document("CustomerAccounts").collection("CustomerAccounts")
            .get().addOnSuccessListener {
                for(data in it.documents){
                    if(data.get("Email")!!.equals(userEmail)){
                        Log.d("userEmail","user email-> ${data.get("Email")}")
                        Log.d("userName","user name->${data.get("Name")}")
                        Log.d("userPhone","user phone->${data.get("Phone Number")}")
                        val placedDetils = hashMapOf(
                            "ItemName" to dishName.text.toString(),
                            "UserName" to data.get("Name").toString(),
                            "UserPhone" to data.get("Phone Number").toString(),
                            "SeatsBooked" to totalSeats.text.toString(),
                            "Price" to dishPrice.text.toString(),
                        )

                        db.collection("Restaurant")
                            .document(email)
                            .collection("Orders")
                            .document()
                            .set(placedDetils).addOnSuccessListener {
                                Toast.makeText(this,"Successfully Booked  your order", Toast.LENGTH_LONG).show()
                            }.addOnFailureListener {
                                Toast.makeText(this,"Failed to Book to your order", Toast.LENGTH_LONG).show()
                            }
                        break


                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Failed to get customer details", Toast.LENGTH_LONG).show()
            }
    }

    private fun addSeats() {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Enter Seats to Booked")
        val dialogLayout=layoutInflater.inflate(R.layout.dialog_with_edittext,null)
        val seats: EditText =dialogLayout.findViewById(R.id.quantityEditText)
        builder.setView(dialogLayout)

        builder.setPositiveButton("Ok"){dialogInterface,i->
           totalSeats.text=seats.text
        }

        builder.show()
    }
}