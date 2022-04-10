package fr.isen.wynar.androiderestaurant.ble
import android.annotation.SuppressLint
import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import fr.isen.wynar.androiderestaurant.databinding.CellBleBinding

internal class BLEAdapter(private var itemsList: MutableList<ScanResult>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<BLEAdapter.MyViewHolder>() {

    private lateinit var binding: CellBleBinding

    internal inner class MyViewHolder(binding: CellBleBinding) : RecyclerView.ViewHolder(binding.root) {
        val mac=binding.mac
        val name=binding.name
        val rssi=binding.rssi
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CellBleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    @SuppressLint("MissingPermission")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.mac.text =  item.device.address
        holder.name.text = if(item.device.name == null){"Unknow Name"}else{item.device.name}
        holder.rssi.text = "${item.rssi} Db"
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }

    class OnClickListener(val clickListener: (item: ScanResult) -> Unit) {
        fun onClick(item: ScanResult) = clickListener(item)
    }
}