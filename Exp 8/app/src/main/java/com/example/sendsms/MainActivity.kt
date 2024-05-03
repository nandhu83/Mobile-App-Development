package com.example.sendsms

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var phoneNumberEditText: EditText
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button

    private val REQUEST_SEND_SMS = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        messageEditText = findViewById(R.id.editTextMessage)
        sendButton = findViewById(R.id.buttonSend)

        // Set onClickListener for the send button
        sendButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), REQUEST_SEND_SMS)
            } else {
                sendMessage()
            }
        }
    }

    private fun sendMessage() {
        val phoneNumber = phoneNumberEditText.text.toString()
        val message = messageEditText.text.toString()

        // Check if the phone number and message are not empty
        if (phoneNumber.isNotEmpty() && message.isNotEmpty()) {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(this, "Message sent successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_SEND_SMS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    sendMessage()
                } else {
                    Toast.makeText(this, "Permission denied. Cannot send SMS.", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

