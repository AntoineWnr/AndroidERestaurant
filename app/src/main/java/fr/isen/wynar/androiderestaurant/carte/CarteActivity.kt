package fr.isen.wynar.androiderestaurant.carte

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.wynar.androiderestaurant.R
import fr.isen.wynar.androiderestaurant.apiRestaurant.Data
import fr.isen.wynar.androiderestaurant.apiRestaurant.Item
import fr.isen.wynar.androiderestaurant.databinding.ActivityCarteBinding
import fr.isen.wynar.androiderestaurant.details.ItemDetailActivity
import fr.isen.wynar.androiderestaurant.panierHandler.BaseActivity
import org.json.JSONObject


class CarteActivity : BaseActivity() {
    private lateinit var binding: ActivityCarteBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityCarteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"



        val jsonObject = JSONObject()
        jsonObject.put("id_shop",1)

        val param = intent.getStringExtra("param1")
        if (param != null) {
            Log.d("Parametres:",param)
        }
        findViewById<TextView>(R.id.textViewRecycler).text = param


        val stringRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->

                val stringResponse = response.toString()
                val item = Gson().fromJson(stringResponse, Data::class.java)

                val arrayOfItems = item.data?.firstOrNull { it.name_fr == param }?.items ?: arrayListOf()
                Log.d("carteActivity", arrayOfItems.toString())
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                val adapter = CarteAdapter(arrayOfItems)
                adapter.setOnItemClickListener(object : CarteAdapter.OnItemClickListener {
                    override fun onItemClick(item: Item) {

                        val intent = Intent(this@CarteActivity, ItemDetailActivity::class.java)
                        intent.putExtra("param1",item)
                        startActivity(intent)
                    }

                })
                binding.recyclerView.adapter = adapter

            },
            {
                Log.d("carteActivity",it.message ?: "NULL")
            })





        // Add the request to the RequestQueue.
        queue.add(stringRequest)





    }
}



