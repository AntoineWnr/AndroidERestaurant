package fr.isen.wynar.androiderestaurant.panierHandler

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.wynar.androiderestaurant.R
import fr.isen.wynar.androiderestaurant.carte.CarteActivity
import java.io.File

open class BaseActivity : AppCompatActivity(){

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.panier -> {
                val currentText = File(cacheDir.absolutePath+"dataPanier.json").readText()
                File(cacheDir.absolutePath+"dataPanier.json").writeText(currentText + "]}")
                val intent = Intent(this, PanierActivity::class.java)
                startActivity(intent)
                true
            }

            else -> {return true}
        }
    }
}