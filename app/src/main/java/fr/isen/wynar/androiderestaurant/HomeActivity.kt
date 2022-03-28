package fr.isen.wynar.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.wynar.androiderestaurant.ble.BLEActivity
import fr.isen.wynar.androiderestaurant.carte.CarteActivity
import fr.isen.wynar.androiderestaurant.databinding.ActivityHomeBinding
import java.io.File

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.textViewEntree.setOnClickListener{
            val intent = Intent(this, CarteActivity::class.java)
            intent.putExtra("param1","Entrées")
            startActivity(intent)
        }

        binding.textViewPlat.setOnClickListener{
            val intent = Intent(this, CarteActivity::class.java)
            intent.putExtra("param1","Plats")
            startActivity(intent)
        }

        binding.textViewDessert.setOnClickListener{
            val intent = Intent(this, CarteActivity::class.java)
            intent.putExtra("param1","Desserts")
            startActivity(intent)
        }

        binding.textViewBLE.setOnClickListener{
            val intent = Intent(this, BLEActivity::class.java)
            startActivity(intent)
        }



    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("DEBUG","Fin HomeActivity")
        File(cacheDir.absolutePath+"dataPanier.json").writeText("")

    }

}

