package com.example.exp3

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creating a Bitmap
        val bg = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888)

        // Setting the Bitmap as background for the ImageView
        val i = findViewById<ImageView>(R.id.imageView)
        i.setBackgroundDrawable(BitmapDrawable(resources, bg))

        // Creating the Canvas Object
        val canvas = Canvas(bg)

        // Creating the Paint Object and set its color & TextSize
        val paint = Paint().apply {
            color = Color.BLUE
            textSize = 50f
        }

        // To draw a Rectangle
        canvas.drawText("Rectangle", 420f, 150f, paint)
        canvas.drawRect(400f, 200f, 650f, 700f, paint)

        // To draw a Circle
        canvas.drawText("Circle", 120f, 150f, paint)
        canvas.drawCircle(200f, 350f, 150f, paint)

        // To draw a Square
        canvas.drawText("Square", 120f, 800f, paint)
        canvas.drawRect(50f, 850f, 350f, 1150f, paint)

        // To draw a Line
        canvas.drawText("Line", 480f, 800f, paint)
        canvas.drawLine(520f, 850f, 520f, 1150f, paint)
    }
}
