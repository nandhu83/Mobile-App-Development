package com.example.ctoiandftocecoversion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputFahrenheit = findViewById<EditText>(R.id.inputFahrenheit)
        val inputCentimeters = findViewById<EditText>(R.id.inputCentimeters)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val resultCelsius = findViewById<TextView>(R.id.resultCelsius)
        val resultInches = findViewById<TextView>(R.id.resultInches)

        convertButton.setOnClickListener {
            val fahrenheit = inputFahrenheit.text.toString().toDoubleOrNull()
            val centimeters = inputCentimeters.text.toString().toDoubleOrNull()

            if (fahrenheit != null && centimeters != null) {
                val celsius = (fahrenheit - 32) * 5 / 9
                val inches = centimeters / 2.54

                resultCelsius.text = "Result in Celsius: %.2f".format(celsius)
                resultInches.text = "Result in Inches: %.2f".format(inches)
            } else {
                if (fahrenheit == null) {
                    inputFahrenheit.error = "Please enter a valid number"
                }
                if (centimeters == null) {
                    inputCentimeters.error = "Please enter a valid number"
                }
            }
        }
    }
}
