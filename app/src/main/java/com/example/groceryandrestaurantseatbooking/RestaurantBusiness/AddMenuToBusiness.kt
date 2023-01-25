package com.example.groceryandrestaurantseatbooking.RestaurantBusiness

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.groceryBusiness
import com.example.groceryandrestaurantseatbooking.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddMenuToBusiness:AppCompatActivity() {
    val db= Firebase.firestore
    lateinit var itemName: EditText
    lateinit var  itemDesc: EditText
    lateinit var  itemPrice: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_menu_to_business)

        itemName=findViewById(R.id.itemNameEditText)
        itemDesc=findViewById(R.id.ItemDescEditText)
        itemPrice=findViewById(R.id.ItemPriceEditText)
        val submit: Button =findViewById(R.id.submitDishtoMenuButton)

        submit.setOnClickListener {
            uploaddataToFirebase()
            Log.d("floating","Inside the Add grocery Class")
        }

}
    private fun uploaddataToFirebase(){
        val useremail= Firebase.auth.currentUser!!.email
        val userId=Firebase.auth.currentUser!!.uid
        val userMap= hashMapOf(
            "Itemname" to itemName.text.toString(),
            "Itemdesc" to itemDesc.text.toString(),
            "Itemprice" to itemPrice.text.toString(),
            "Email" to useremail
        )
        db.collection("Restaurant").document(useremail.toString()).collection("CurrentMenu").document().set(userMap)
            .addOnSuccessListener {
                Toast.makeText(this,"Successfully Added", Toast.LENGTH_LONG).show()
                itemName.text.clear()
                itemDesc.text.clear()
                itemPrice.text.clear()
                startActivity(Intent(this, RestaurantBusiness::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this,"Failed To Add", Toast.LENGTH_LONG).show()
            }
    }
}




