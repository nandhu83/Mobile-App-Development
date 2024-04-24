package com.example.myapplication



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputEditText: EditText = findViewById(R.id.inputEditText)
        val convertButton: Button = findViewById(R.id.convertButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        convertButton.setOnClickListener {
            val inputTemp = inputEditText.text.toString().toDoubleOrNull()
            if (inputTemp != null) {
                // Assuming the user enters temperature in Celsius and wants it in Fahrenheit
                val resultFahrenheit = (inputTemp * 9/5) + 32
                val result = round(resultFahrenheit * 100) / 100 // Rounding to 2 decimal places
                resultTextView.text = getString(R.string.conversion_result, result)
            } else {
                resultTextView.text = "Please enter a valid number"
            }
        }
    }
}
