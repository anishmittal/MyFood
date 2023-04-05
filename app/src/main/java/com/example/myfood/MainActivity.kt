package com.example.myfood

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: MyAdapter
    private lateinit var items: List<Item>

    //private val originalList = mutableListOf<Item>()
    private var filteredList = mutableListOf<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        searchView = findViewById(R.id.searchView)
        var cart = findViewById<Button>(R.id.cart)

        cart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, Cart::class.java)
            startActivity(intent)
        })

        // set up the list of items
        items = listOf(
            Item("cake", R.drawable.cake),
            Item("chapati", R.drawable.chapati),
            Item("dessert", R.drawable.dessert),
            Item("noodles", R.drawable.noodles),
            Item("pizza", R.drawable.pizza),
            Item("salad", R.drawable.salad),
            Item("seafood", R.drawable.seafood),
            Item("toast", R.drawable.toast)
        )

        filteredList = items.toMutableList()
        // set up the adapter
        adapter = MyAdapter(this, filteredList)
        listView.adapter = adapter

        // set up the search bar
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter.filter(newText)
                filterList(newText ?: "")
                return true
            }

        })




//        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            val selectedItem = parent.getItemAtPosition(position) as String
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("selectedItem", selectedItem)
//            startActivity(intent)
//        }
    }
    private fun filterList(query: String) {
        filteredList.clear()
        items.forEach { item ->
            if (item.name.contains(query, ignoreCase = true)) {
                filteredList.add(item)
            }
        }
        adapter.notifyDataSetChanged()
    }
}