package com.example.panandpincodevalidation
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etPanCard: EditText = findViewById(R.id.etPanCard)
        val etPincode: EditText = findViewById(R.id.etPincode)
        val btnValidate: Button = findViewById(R.id.btnValidate)

        btnValidate.setOnClickListener {
            val panCard = etPanCard.text.toString().trim()
            val pincode = etPincode.text.toString().trim()

            if (validatePanCard(panCard) && validatePincode(pincode)) {
                Toast.makeText(this, "Validation Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Validation Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validatePanCard(panCard: String): Boolean {
        return panCard.length == 10 && panCard.all { it.isLetterOrDigit() }
    }

    private fun validatePincode(pincode: String): Boolean {
        return pincode.length == 6 && pincode.all { it.isDigit() }
    }
}
