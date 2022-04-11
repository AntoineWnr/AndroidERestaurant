package fr.isen.wynar.androiderestaurant.panierHandler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.wynar.androiderestaurant.R
import fr.isen.wynar.androiderestaurant.apiRestaurant.Item
import fr.isen.wynar.androiderestaurant.carte.CarteAdapter
import fr.isen.wynar.androiderestaurant.databinding.CellBleBinding
import fr.isen.wynar.androiderestaurant.databinding.CellPanierBinding

class PanierAdapter(private val arrayList: ArrayList<PanierData>) : RecyclerView.Adapter<PanierAdapter.PanierViewHolder>()  {

    private lateinit var binding: CellPanierBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanierAdapter.PanierViewHolder {
        binding = CellPanierBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PanierViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PanierAdapter.PanierViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class PanierViewHolder(binding: CellPanierBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.namePlat
        val total : TextView = binding.totalPlat
        val qty : TextView = binding.qtyPlat
    }
}

