package com.example.myfood


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*


class Cart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)

        val priceView = findViewById<TextView>(R.id.price)
        val placeOrder = findViewById<Button>(R.id.placeOrder)

        val sharedPreferences = getSharedPreferences("items", MODE_PRIVATE)
        val spPrice = getSharedPreferences("price", MODE_PRIVATE)
        var totalPrice = spPrice.getFloat("price",0.0f)

        priceView.text = "Total price is Rs. "+totalPrice.toString()
        val allEntries: Map<String, *> = sharedPreferences.all

        var numberOfPairs = allEntries.size
        Log.d("number", ""+numberOfPairs)

        val itemList = ArrayList<String>()

        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value.toString()
            if (value is String) {
                itemList.add(value)
            }
        }

//        val itemList: List<String> = ArrayList(items)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)
//
        val listView: ListView = findViewById(R.id.listView)
        listView.setAdapter(adapter)

        placeOrder.setOnClickListener(View.OnClickListener {
            val sharedPreferences = getSharedPreferences("items", MODE_PRIVATE)
            val spPrice = getSharedPreferences("price", MODE_PRIVATE)

            val editor = sharedPreferences.edit()
            val editorPrice = spPrice.edit()

            editor.clear()
            editorPrice.clear()

            editor.apply()
            editorPrice.apply()
            val intent = Intent(this, Orders::class.java)
            startActivity(intent)
            finish()

        })

    }
}