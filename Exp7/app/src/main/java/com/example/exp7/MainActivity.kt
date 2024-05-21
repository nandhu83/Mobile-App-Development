package com.example.exp7

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 1
    private lateinit var editTextNetworkOperator: EditText
    private lateinit var editTextPhoneType: EditText
    private lateinit var editTextNetworkCountryISO: EditText
    private lateinit var editTextSimCountryISO: EditText
    private lateinit var editTextDeviceSoftwareVersion: EditText
    private lateinit var telephonyManager: TelephonyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNetworkOperator = findViewById(R.id.edit_text_network_operator)
        editTextPhoneType = findViewById(R.id.edit_text_phone_type)
        editTextNetworkCountryISO = findViewById(R.id.edit_text_network_country_iso)
        editTextSimCountryISO = findViewById(R.id.edit_text_sim_country_iso)
        editTextDeviceSoftwareVersion = findViewById(R.id.edit_text_device_software_version)
        val buttonGetTelephonyInfo: Button = findViewById(R.id.button_get_telephony_info)

        telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        buttonGetTelephonyInfo.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), PERMISSION_REQUEST_CODE)
            } else {
                displayTelephonyInfo()
            }
        }
    }

    private fun displayTelephonyInfo() {
        val networkOperator = telephonyManager.networkOperatorName
        val phoneType = getPhoneTypeString(telephonyManager.phoneType)
        val networkCountryISO = telephonyManager.networkCountryIso
        val simCountryISO = telephonyManager.simCountryIso
        val deviceSoftwareVersion = telephonyManager.deviceSoftwareVersion

        editTextNetworkOperator.setText(networkOperator)
        editTextPhoneType.setText(phoneType)
        editTextNetworkCountryISO.setText(networkCountryISO)
        editTextSimCountryISO.setText(simCountryISO)
        editTextDeviceSoftwareVersion.setText(deviceSoftwareVersion)
    }

    private fun getPhoneTypeString(phoneType: Int): String {
        return when (phoneType) {
            TelephonyManager.PHONE_TYPE_CDMA -> "CDMA"
            TelephonyManager.PHONE_TYPE_GSM -> "GSM"
            TelephonyManager.PHONE_TYPE_SIP -> "SIP"
            TelephonyManager.PHONE_TYPE_NONE -> "None"
            else -> "Unknown"
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayTelephonyInfo()
            } else {
                // Permission denied
                editTextNetworkOperator.setText("Permission denied")
                editTextPhoneType.setText("")
                editTextNetworkCountryISO.setText("")
                editTextSimCountryISO.setText("")
                editTextDeviceSoftwareVersion.setText("")
            }
        }
    }
}
