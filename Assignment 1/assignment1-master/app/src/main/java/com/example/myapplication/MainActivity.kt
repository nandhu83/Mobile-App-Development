package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countTextView: TextView = findViewById(R.id.countTextView)
        val checkInButton: Button = findViewById(R.id.checkInButton)
        val checkOutButton: Button = findViewById(R.id.checkOutButton)

        checkInButton.setOnClickListener {
            count++
            countTextView.text = count.toString()
        }

        checkOutButton.setOnClickListener {
            if (count > 0) {
                count--
                countTextView.text = count.toString()
            }
        }
    }
}
