package com.example.exp1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val tv1 : TextView = findViewById(R.id.tv1)
        val ll: LinearLayout = findViewById(R.id.main)
        val btn1 : Button = findViewById(R.id.btn1)
        val btn2 : Button = findViewById(R.id.btn2)
        val btn3 : Button = findViewById(R.id.btn3)
        var size : Float = 10f
        var i : Int = 0
        btn1.setOnClickListener{
            tv1.setTextSize(size)
            size = (size+5)%50
        }
        btn2.setOnClickListener {
            when(i%3)
            {
                0 -> tv1.setTextColor(Color.RED)
                1 -> tv1.setTextColor(Color.GREEN)
                2 -> tv1.setTextColor(Color.BLUE)
            }
            i=i+1
        }
        btn3.setOnClickListener {
            when(i%3)
            {
                0 -> ll.setBackgroundColor(Color.RED)
                1 -> ll.setBackgroundColor(Color.GREEN)
                2 -> ll.setBackgroundColor(Color.BLUE)
            }
            i=i+1
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}