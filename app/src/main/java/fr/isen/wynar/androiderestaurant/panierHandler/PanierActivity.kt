package fr.isen.wynar.androiderestaurant.panierHandler

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import fr.isen.wynar.androiderestaurant.databinding.ActivityPanierBinding
import java.io.File
import java.io.StringReader

class PanierActivity : BaseActivity() {
    private lateinit var binding: ActivityPanierBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPanierBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentText = File(cacheDir.absolutePath+"dataPanier.json").readText()
        val gson = Gson().fromJson(currentText,PlatModel::class.java)

        Log.d("Mashallah",gson.plats.toString())

        binding.recyclerViewPanier.layoutManager = LinearLayoutManager(this)
        val adapter = PanierAdapter(gson.plats)
        binding.recyclerViewPanier.adapter = adapter


    }
}