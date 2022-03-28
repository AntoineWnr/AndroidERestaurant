package fr.isen.wynar.androiderestaurant.ble

import android.Manifest
import android.bluetooth.le.ScanCallback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.wynar.androiderestaurant.databinding.ActivityBleBinding
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.content.PermissionChecker

class BLEActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBleBinding
    private var isScanning = false
/*
    private val bleScanner = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            Log.d("ScanDeviceActivity", "onScanResult(): ${result?.device?.address} - ${result?.device?.name}")
        }
    }

    private val bluetoothLeScanner: BluetoothLeScanner
        get() {
            val bluetoothManager = applicationContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            val bluetoothAdapter = bluetoothManager.adapter
            return bluetoothAdapter.bluetoothLeScanner
        }

    class ListDevicesAdapter(context: Context?, resource: Int) : ArrayAdapter<String>(context!!, resource) {
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        handlePlayPauseButton()
        Log.d("ScanDeviceActivity", "onCreate()")

    }
    /*
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        Log.d("ScanDeviceActivity", "onStart()")
        super.onStart()
        when (PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            PackageManager.PERMISSION_GRANTED -> bluetoothLeScanner.startScan(bleScanner)
            else -> requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> when (grantResults) {
                intArrayOf(PackageManager.PERMISSION_GRANTED) -> {
                    Log.d("ScanDeviceActivity", "onRequestPermissionsResult(PERMISSION_GRANTED)")
                    bluetoothLeScanner.startScan(bleScanner)
                }
                else -> {
                    Log.d("ScanDeviceActivity", "onRequestPermissionsResult(not PERMISSION_GRANTED)")
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
*/
    private fun handlePlayPauseButton(){
        binding.buttonStart.setOnClickListener {
            if (isScanning) {
                binding.progressBarBLE.isIndeterminate = false
                binding.textViewBLEState.text = "Recherche d'appareil bluetooh"
            } else {
                binding.progressBarBLE.isIndeterminate = true
                binding.textViewBLEState.text = "Lancer la recherche"
            }
        }
    }
/*
    override fun onStop() {
        Log.d("ScanDeviceActivity", "onStop()")
        super.onStop()
        bluetoothLeScanner.stopScan(bleScanner)
    }
*/
}