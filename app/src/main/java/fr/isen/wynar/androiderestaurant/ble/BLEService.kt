package fr.isen.wynar.androiderestaurant.ble

import android.bluetooth.BluetoothGattCharacteristic
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class BLEService(name: String, characteristics: MutableList<BluetoothGattCharacteristic>) :
    ExpandableGroup<BluetoothGattCharacteristic>(name, characteristics)