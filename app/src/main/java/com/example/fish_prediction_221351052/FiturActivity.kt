package com.example.fish_prediction_221351052

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class FiturActivity : AppCompatActivity() {
    // 1. Declare the variable for the ImageButton.
    // 'lateinit' means we promise to initialize it before using it.
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitur) // The layout is inflated here

        // Enable the Up button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 2. Initialize the backButton after setContentView
        backButton = findViewById<ImageButton>(R.id.backButton)

        // 3. Set the OnClickListener for the backButton
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Handles the back press
        }
    }

    // This method is called when the Up button in the action bar is pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}