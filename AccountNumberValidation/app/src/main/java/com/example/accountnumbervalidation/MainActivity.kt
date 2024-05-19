package com.example.accountnumbervalidation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val accountNumberEditText = findViewById<EditText>(R.id.accountNumberEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val validateButton = findViewById<Button>(R.id.validateButton)

        validateButton.setOnClickListener {
            val accountNumber = accountNumberEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (accountNumber.isEmpty() || password.isEmpty()) {
                showToast("Account number and password cannot be empty")
            } else if (accountNumber.length < 10) {
                showToast("Account number should be at least 10 numbers long")
            } else if (password.length < 4) {
                showToast("Password should be at least 4 characters long")
            } else {
                showToast("Validation successful!")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
