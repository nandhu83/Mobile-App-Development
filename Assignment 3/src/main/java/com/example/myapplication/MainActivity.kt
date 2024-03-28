package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val unitRadioGroup: RadioGroup = findViewById(R.id.unitRadioGroup)
        val meterRadioButton: RadioButton = findViewById(R.id.meterRadioButton)

        calculateButton.setOnClickListener {
            val weightText = weightEditText.text.toString()
            val heightText = heightEditText.text.toString()
            val isMeter = meterRadioButton.isChecked

            if (weightText.isNotEmpty() && heightText.isNotEmpty()) {
                val weight = weightText.toDouble()
                var height = heightText.toDouble()

                // Convert height to meters if it's in centimeters
                if (!isMeter) {
                    height /= 100.0
                }

                val bmi = weight / height.pow(2)
                resultTextView.text = "Your BMI: %.2f".format(bmi)
            } else {
                resultTextView.text = "Please enter valid numbers"
            }
        }
    }
}
