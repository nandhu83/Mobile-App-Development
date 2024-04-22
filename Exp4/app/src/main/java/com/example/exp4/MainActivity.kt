package com.example.exp4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load Basic Details Fragment
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_basic_details, BasicDetailsFragment())
        fragmentTransaction.commit()

        // Load Mark Details Fragment
        val fragmentTransaction2: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction2.replace(R.id.container_mark_details, MarkDetailsFragment())
        fragmentTransaction2.commit()
    }
}
