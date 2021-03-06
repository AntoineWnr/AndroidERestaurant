package fr.isen.wynar.androiderestaurant.ble

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.wynar.androiderestaurant.R
import fr.isen.wynar.androiderestaurant.databinding.ActivityBleBinding
import fr.isen.wynar.androiderestaurant.panierHandler.BaseActivity


class BLEActivity : BaseActivity() {

    companion object{
        private const val ALL_PERMISSION_REQUEST_CODE = 1
        const val DEVICE_KEY = "device"
    }
    private var scanning:Boolean=false
    private lateinit var binding : ActivityBleBinding
    private val bluetoothAdapter: BluetoothAdapter ? by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private var itemsList = ArrayList<ScanResult>()

    private lateinit var listBleAdapter: BLEAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        when {
            bluetoothAdapter?.isEnabled == true ->
                binding.imgPlayBle.setOnClickListener {
                    startLeScanBLEWithPermission(!scanning)
                    Log.d("TEST2", "ICI")
                }
            bluetoothAdapter != null ->
                askBluetoothPermission()
            else -> {
                displayNoBleAvailable()
            }
        }
        val recyclerBle: RecyclerView = binding.ListBleScan
        listBleAdapter = BLEAdapter(itemsList, BLEAdapter.OnClickListener { item ->
            onListBleClick(item)
        })
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerBle.layoutManager = layoutManager
        recyclerBle.adapter = listBleAdapter
    }


    @SuppressLint("MissingPermission")
    private fun onListBleClick(item: ScanResult) {
        if(item.device.name.isNullOrEmpty()){
            Toast.makeText(this@BLEActivity, "Unknown name", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@BLEActivity, item.device.name.toString(), Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, BLEDeviceActivity::class.java)
        intent.putExtra(DEVICE_KEY, item.device)
        startActivity(intent)
    }

    override fun onStop(){
        super.onStop()
        startLeScanBLEWithPermission(false)
    }

    private fun startLeScanBLEWithPermission(enable: Boolean){
        if (checkAllPermissionGranted()) {
            Log.d("startScan", "ICI2")
            startLeScanBLE(enable)
        }else{
            ActivityCompat.requestPermissions(this, getAllPermissions() ,ALL_PERMISSION_REQUEST_CODE)
            Log.d("startScan2", "ICI3")
        }
    }

    private fun checkAllPermissionGranted(): Boolean {
        return getAllPermissions().all { permission ->
            ActivityCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun getAllPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLeScanBLE(enable: Boolean) {

        bluetoothAdapter?.bluetoothLeScanner?.apply {
            if(enable) {
                val handler = Handler(mainLooper)
                handler.postDelayed({
                    scanning=false// stop scanning here
                    binding.imgPlayBle.setImageResource(R.drawable.ic_baseline_arrow_right_24)
                    binding.textViewBLEState.text = "Scanning"
                    binding.progressBarBLE.isIndeterminate = false
                    stopScan(scanCallBack)
                }, 15000)
                scanning=true
                startScan(scanCallBack)
            }else{
                scanning=false
                stopScan(scanCallBack)
            }
            btnPlayClick()
        }
    }

    private val scanCallBack = object : ScanCallback(){
        override fun onScanResult(callBackType : Int, result:ScanResult){
            Log.d("BLEScanActivity","${result.device.address} - ${result.rssi}")
            addToList(result)

        }
    }

    private fun addToList(res:ScanResult){
        val index:Int = itemsList.indexOfFirst{ it.device.address==res.device.address }
        if(index == -1){
            itemsList.add(res)
        }else{
            itemsList[index]=res
        }
        itemsList.sortBy { kotlin.math.abs(it.rssi) }
        listBleAdapter.notifyDataSetChanged()
    }

    private fun displayNoBleAvailable() {
        binding.imgPlayBle.isVisible = false
        binding.textViewBLEState.text="Lancer le scan"
        binding.progressBarBLE.isIndeterminate=true
    }

    private fun btnPlayClick() {
        binding.imgPlayBle.isVisible = true
        if (scanning) {
            binding.imgPlayBle.setImageResource(android.R.drawable.ic_media_pause)
            binding.textViewBLEState.text = "Chargement"
            binding.progressBarBLE.isIndeterminate = true
        } else {
            binding.imgPlayBle.setImageResource(android.R.drawable.ic_media_play)
            binding.textViewBLEState.text = "Lancer le scan"
            binding.progressBarBLE.isIndeterminate = false
        }
    }

    private fun askBluetoothPermission() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            startActivityForResult(enableBtIntent, ALL_PERMISSION_REQUEST_CODE)
        }

    }


}