package com.example.usernamepasswordvalidation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dollarAmountInput = findViewById<EditText>(R.id.dollarAmount)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val rupeeAmountText = findViewById<TextView>(R.id.rupeeAmount)

        convertButton.setOnClickListener {
            val dollarAmount = dollarAmountInput.text.toString().toDoubleOrNull()
            if (dollarAmount != null) {
                val rupeeAmount = convertDollarsToRupees(dollarAmount)
                rupeeAmountText.text = DecimalFormat("#.##").format(rupeeAmount)
            } else {
                rupeeAmountText.text = "Invalid input"
            }
        }
    }

    private fun convertDollarsToRupees(dollars: Double): Double {
        val exchangeRate = 74.50
        return dollars * exchangeRate
    }
}
