package fr.isen.wynar.androiderestaurant.details

import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.wynar.androiderestaurant.apiRestaurant.Item
import fr.isen.wynar.androiderestaurant.databinding.ActivityItemDetailBinding
import fr.isen.wynar.androiderestaurant.panierHandler.BaseActivity
import fr.isen.wynar.androiderestaurant.panierHandler.PanierData
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class ItemDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityItemDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val param = intent.getSerializableExtra("param1") as Item
        //Log.d("ItemDetail",param.ingredients.toString())

        Picasso.get().load(param.images[0])
            .into(binding.imageDetail)
        var ingredientText : String = ""
        binding.textNameFood.text = param.name_fr
        for (ingredient in param.ingredients){
            ingredientText += ingredient.name_fr + ","
        }

        binding.textIngredient.text = ingredientText

        val btnAdd = binding.buttonAdd
        val btnRemove = binding.buttonRemove
        val btnTotal = binding.buttonTotal
        var qty: Int = 0
        var total : Int = 0
        btnAdd.setOnClickListener{
            qty += 1
            binding.textQuantity.text = qty.toString()
            total = qty * param.prices[0].price.toString().toInt()
            btnTotal.text = "Total : " + total.toString() + "€"

        }
        btnRemove.setOnClickListener{
            if(qty>0) {
                qty -= 1
                binding.textQuantity.text = qty.toString()
                total = qty * param.prices[0].price.toString().toInt()
                btnTotal.text = "Total : " + (qty * param.prices[0].price.toString().toInt()).toString() + "€"

            }else{
                Toast.makeText(this,"Pas de valeur négative",Toast.LENGTH_LONG).show()
            }
        }
        btnTotal.setOnClickListener {

            if(total>0) {
                Toast.makeText(this, "Ajout de "+ qty + " "+ param.name_fr + " au panier !",Toast.LENGTH_LONG).show()

                var panierData = PanierData(param.name_fr, qty, total)
                val jsonString = Gson().toJson(panierData)
                var currentText = ""

                val jsonObjectIn = JSONObject()
                jsonObjectIn.put("name",param.name_fr)
                jsonObjectIn.put("qty",qty)
                jsonObjectIn.put("total",total)

                val jsonArray = JSONArray()
                jsonArray.put(jsonObjectIn)

                val jsonObject = JSONObject()
                jsonObject.put("plats",jsonArray)

                File(cacheDir.absolutePath+"dataPanier.json").writeText(jsonObject.toString())



            }
        }
    }


}