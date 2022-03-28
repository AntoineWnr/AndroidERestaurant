package fr.isen.wynar.androiderestaurant.carte

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.wynar.androiderestaurant.R
import fr.isen.wynar.androiderestaurant.apiRestaurant.Item

class CarteAdapter(private val arrayList: ArrayList<Item>) : RecyclerView.Adapter<CarteAdapter.ProductViewHolder>() {

    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(item: Item)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cell_carte,
            parent, false
        )
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = arrayList[position]
        holder.price.text = currentItem.prices[0].price + "€"
        holder.name.text = currentItem.name_fr
        Picasso.get().load(currentItem.images[0].ifEmpty { null })
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            mListener.onItemClick(currentItem)
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.carteTitre)
        val image: ImageView = itemView.findViewById(R.id.imageFood)
        val price : TextView = itemView.findViewById(R.id.textPrice)
    }

    override fun getItemCount(): Int {
        return arrayList.size ?: 0
    }


}

