package fr.isen.wynar.androiderestaurant.panierHandler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import fr.isen.wynar.androiderestaurant.databinding.CellPanierBinding

class PanierAdapter(private val arrayList: ArrayList<PanierData>) : RecyclerView.Adapter<PanierAdapter.PanierViewHolder>()  {

    private lateinit var binding: CellPanierBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanierViewHolder {
        binding = CellPanierBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PanierViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PanierViewHolder, position: Int) {
        val item = arrayList[position]
        holder.name.text =  "Plats : " + item.name
        holder.total.text = "Total : " + item.total.toString() + "€"
        holder.qty.text = "Quantité : ${item.qty} "
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class PanierViewHolder(binding: CellPanierBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.namePlat
        val total : TextView = binding.totalPlat
        val qty : TextView = binding.qtyPlat
    }
}

