package com.example.exp10
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var text: EditText
    private lateinit var btnText: Button
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.Text)
        btnText = findViewById(R.id.btnText)

        // create an object textToSpeech and adding features into it
        textToSpeech = TextToSpeech(applicationContext) { status ->
            // if No error is found then only it will run
            if (status != TextToSpeech.ERROR) {
                // To Choose language of speech
                textToSpeech.language = Locale.UK
            }
        }

        // Adding OnClickListener
        btnText.setOnClickListener {
            textToSpeech.speak(text.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onDestroy() {
        // Shutdown TextToSpeech engine when activity is destroyed to prevent memory leaks
        if (textToSpeech != null) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
