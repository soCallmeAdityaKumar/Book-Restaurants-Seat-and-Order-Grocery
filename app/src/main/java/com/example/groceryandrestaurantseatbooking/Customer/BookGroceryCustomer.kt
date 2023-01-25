package com.example.groceryandrestaurantseatbooking.Customer

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.Grocery
import com.example.groceryandrestaurantseatbooking.GroceryBusiness.groceryBusiness
import com.example.groceryandrestaurantseatbooking.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BookGroceryCustomer:AppCompatActivity() {
    val auth=Firebase.auth
    val db=Firebase.firestore
    lateinit var user:User
    lateinit var email:String
    lateinit var itemName:TextView
    lateinit var itemDesc:TextView
    lateinit var itemprice:TextView
    lateinit var textQuantity:TextView
    lateinit var totalAmount:TextView
    lateinit var confirm: Button
    lateinit var evaluatePrice:Button
    lateinit var grocery: Grocery
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_grocery_customer)
        itemName=findViewById(R.id.itemGroceryNameOrder)
         itemDesc=findViewById(R.id.itemDecGroceryOrder)
         itemprice=findViewById(R.id.itemPriceGroceryOrder)
         textQuantity=findViewById(R.id.EnterQuantityGroceryOrder)
         totalAmount=findViewById(R.id.totalAmountTextViewGrocery)
         confirm=findViewById(R.id.orderplaceGroceryCustomerButton)
        evaluatePrice=findViewById(R.id.evaluateOrderPrice)
        callquantityDialog()
        grocery = intent.getParcelableExtra("grocery")!!

        itemName.text= grocery.name
        itemDesc.text= grocery.Description
        itemprice.text= grocery.Price
         email=grocery.email

        Log.d("get","we got email of business man -> $email")

        evaluatePrice.setOnClickListener {
            if(textQuantity.text!=null){
                totalAmount.text=((itemprice.text.toString().toInt())*(textQuantity.text.toString().toInt())).toString()
            }else if(textQuantity.text==null){
                Toast.makeText(this,"Please enter the quantity",Toast.LENGTH_LONG).show()
            }
        }

        confirm.setOnClickListener {
            orderProgress()
        }

    }

    private fun callquantityDialog() {
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Enter Quantity")
        val dialogLayout=layoutInflater.inflate(R.layout.dialog_with_edittext,null)
        val quantityEditText:EditText=dialogLayout.findViewById(R.id.quantityEditText)
        builder.setView(dialogLayout)

        builder.setPositiveButton("Ok"){dialogInterface,i->
                     textQuantity.text= quantityEditText.text
        }

        builder.show()

    }

    private fun orderProgress() {
        //RazorPay Integration

        val userEmail=auth.currentUser!!.email

        Log.d("usersdetails","email-> $userEmail")


        db.collection("UserInfo").document("CustomerAccounts").collection("CustomerAccounts")
            .get().addOnSuccessListener {
                for(data in it.documents){
                    if(data.get("BusinessEmail")!!.equals(userEmail)){
                        Log.d("userEmail","user email-> ${data.get("Email")}")
                        Log.d("userName","user name->${data.get("Name")}")
                        Log.d("userPhone","user phone->${data.get("Phone Number")}")

                        val placedDetils = hashMapOf(
                            "ItemName" to itemName.text.toString(),
                            "UserName" to data.get("Name").toString(),
                            "UserPhone" to data.get("Phone Number").toString(),
                            "TotalQuantity" to textQuantity.text.toString(),
                            "TotalAmount" to totalAmount.text.toString()
                        )

                        db.collection("Grocery")
                            .document(email)
                            .collection("Orders")
                            .document()
                            .set(placedDetils).addOnSuccessListener {
                                Toast.makeText(this,"Successfully placed your order",Toast.LENGTH_LONG).show()
                            }.addOnFailureListener {
                                Toast.makeText(this,"Failed to place to your order",Toast.LENGTH_LONG).show()
                            }
                        break
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Failed to get customer details",Toast.LENGTH_LONG).show()
            }

    }
}

