package fr.isen.wynar.androiderestaurant.panierHandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.wynar.androiderestaurant.R
import fr.isen.wynar.androiderestaurant.apiRestaurant.Data
import fr.isen.wynar.androiderestaurant.carte.CarteAdapter
import fr.isen.wynar.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.wynar.androiderestaurant.databinding.ActivityPanierBinding

class PanierActivity : BaseActivity() {
    private lateinit var binding: ActivityPanierBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPanierBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val item = Gson().fromJson(cacheDir.absolutePath+"dataPanier.json", PlatModel::class.java)
        Log.d("ICILA", item.plats.toString())


        binding.recyclerViewPanier.layoutManager = LinearLayoutManager(this)
        //val adapter = PanierAdapter(arrayOfPlats)


    }
}