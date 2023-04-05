package com.example.myfood

import android.R.attr.button
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlin.random.Random


class MyAdapter(context: Context, private val items: List<Item>) : ArrayAdapter<Item>(context, R.layout.food_items, items) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.food_items, parent, false)
        }

        val item = getItem(position)
        val textView = view!!.findViewById<TextView>(R.id.textView)
        val icon = view.findViewById<ImageView>(R.id.imageView)
        val price = view!!.findViewById<TextView>(R.id.price)

        textView.text = item?.name
        val randomNumber = Random.nextInt(0, 100)
        //Log.d("MyAdapter: randomNumber-- = " ,""+randomNumber )
        Log.d("Number",randomNumber.toString() )
        price.text ="Rs. "+randomNumber
        icon.setImageResource(item?.iconResId ?: 0)

        view.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("selectedItem", item?.name)
            Log.d("MyAdapter: randomNumber = " ,""+randomNumber )
            intent.putExtra("price", ""+randomNumber)
            Log.d("MyAdapter: selectedItem" ,""+item?.name )
            context.startActivity(intent)
        }



        return view
    }

}
