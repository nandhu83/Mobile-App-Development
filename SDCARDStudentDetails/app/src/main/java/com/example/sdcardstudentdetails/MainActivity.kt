package com.example.sdcardstudentdetails

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var etStudentName: EditText
    private lateinit var etRollNumber: EditText
    private lateinit var etCGPA: EditText
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button
    private lateinit var tvLoadedData: TextView

    private val fileName = "student_data.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etStudentName = findViewById(R.id.etStudentName)
        etRollNumber = findViewById(R.id.etRollNumber)
        etCGPA = findViewById(R.id.etCGPA)
        btnSave = findViewById(R.id.btnSave)
        btnLoad = findViewById(R.id.btnLoad)
        tvLoadedData = findViewById(R.id.tvLoadedData)

        etStudentName.setOnClickListener { clearFields() }
        etRollNumber.setOnClickListener { clearFields() }
        etCGPA.setOnClickListener { clearFields() }

        btnSave.setOnClickListener {
            saveData()
        }

        btnLoad.setOnClickListener {
            loadData()
        }

        checkPermissions()
    }

    private fun clearFields() {
        etStudentName.text.clear()
        etRollNumber.text.clear()
        etCGPA.text.clear()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 2)
        }
    }

    private fun saveData() {
        val studentName = etStudentName.text.toString()
        val rollNumber = etRollNumber.text.toString()
        val cgpa = etCGPA.text.toString()

        if (studentName.isEmpty() || rollNumber.isEmpty() || cgpa.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val studentData = "$studentName,$rollNumber,$cgpa"
        val file = File(getExternalFilesDir(null), fileName)

        file.writeText(studentData)

        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val file = File(getExternalFilesDir(null), fileName)

        if (!file.exists()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            return
        }

        val studentData = file.readText()
        val data = studentData.split(",")

        if (data.size == 3) {
            val displayData = "Student Name: ${data[0]}\nRoll Number: ${data[1]}\nCGPA: ${data[2]}"
            tvLoadedData.text = displayData
            Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show()
        }
    }
}
