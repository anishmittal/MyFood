package com.example.myfood

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetailActivity : AppCompatActivity() {

    val map = mutableMapOf(
        "cake" to "Order any type of cakes and in any shape.A cake is a sweet food made by baking a mixture of flour, eggs, sugar, and fat in an oven. Cakes may be large and cut into slices or small and intended for one person only.",
        "chapati" to "Order all kinds of chapattis. Tawa chapati, Tandoor chapati etc. A round flat unleavened bread of India that is usually made of whole wheat flour and cooked on a griddle.",
        "dessert" to "Order desserts which are smooth, tangy, crumbly, fluffy, heavy, delicate, homely, refreshing, wholesome, indulgent, sinful, scrumptious. Experiment with some literary mix and match to create the most enticing message you can.",
        "noodles" to "Spicy noodles, masala noodles and plain noodles. All noodles available.noodle, a cooked egg-and-flour paste prominent in European and Asian cuisine, generally distinguished from pasta by its elongated ribbonlike form.",
        "pizza" to "Pizza-your favourite meal is here. You can order any type of pizza. pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly",
        "salad" to "Order healthy and spicy salad.green vegetables (as lettuce) often with tomato, cucumber, or radish served with dressing. : a cold dish (as of meat, shellfish, fruit, or vegetables) usually prepared with a dressing.",
        "seafood" to "Seafood includes all commercially captured or farmed freshwater and saltwater fish, molluscan shellfish, and crustaceans. Molluscan shellfish (or mollusks) and crustaceans (shrimp, lobster, and crayfish) are both commonly referred to as shellfish.",
        "toast" to "Toast is sliced bread that has been browned by radiant heat. The browning is the result of a Maillard reaction altering the flavor of the bread and making it firmer. The firm surface is easier to spread toppings on and the warmth can help butter reach its melting point."
    )


//    mapImages["dessert"] =  R.mipmap.cake_desc
//        "noodles" to R.mipmap.noodles_desc,
//        "chapati" to R.mipmap.chapati_desc,
//        "salad" to R.mipmap.salad_desc,
//        "seafood" to R.mipmap.seafood_desc,
//        "toast" to R.mipmap.toast_desc,
//        "pizza" to R.mipmap.pizza_desc,
val mapImages = mutableMapOf<String, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)


        loadImages()



        val selectedItem = intent.getStringExtra("selectedItem")
        val price = intent.getStringExtra("price")
        Log.d("DetailActivity: price is" ,""+price )
        val textView = findViewById<TextView>(R.id.textView)
        val imgView = findViewById<ImageView>(R.id.imageView)
        val buttonCart = findViewById<Button>(R.id.button_cart)

        textView.text = map.get(selectedItem)
        mapImages[selectedItem]?.let { imgView.setImageResource(it) }

        Log.d("selectedItem" ,""+selectedItem )

        buttonCart.setOnClickListener(View.OnClickListener {
            val sharedPreferences = getSharedPreferences("items", MODE_PRIVATE)
            val spPrice = getSharedPreferences("price", MODE_PRIVATE)
            val allEntries: Map<String, *> = sharedPreferences.all

            var numberOfPairs = allEntries.size
            var prevPrice = spPrice.getFloat("price",0.0f)

            Log.d("price = ",""+price)
            Log.d("prevPrice = ",""+prevPrice)
            val editorPrice = spPrice.edit()
            var totalPrice = price!!.toFloat() + prevPrice
            Log.d("totalPrice = ",""+totalPrice)
            editorPrice.putFloat("price", totalPrice)
            editorPrice.apply()

            val editor = sharedPreferences.edit()
            editor.putString("Item : "+numberOfPairs,selectedItem )
            editor.apply()
            Log.d("number", ""+numberOfPairs)
            Toast.makeText(this@DetailActivity, "Item added to Cart", Toast.LENGTH_SHORT).show()
        })
    }

    private fun loadImages() {
        mapImages["cake"] =  R.mipmap.cake_desc
        mapImages["dessert"] =  R.mipmap.dessert_desc
        mapImages["chapati"] =  R.mipmap.chapati_desc
        mapImages["salad"] =  R.mipmap.salad_desc
        mapImages["seafood"] =  R.mipmap.seafood_desc
        mapImages["toast"] =  R.mipmap.toast_desc
        mapImages["pizza"] =  R.mipmap.pizza_desc
        mapImages["noodles"] =  R.mipmap.noodles_desc
    }
}