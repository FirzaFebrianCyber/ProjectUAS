package com.example.fish_prediction_221351052

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class TentangActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        backButton = findViewById<ImageButton>(R.id.backButton)

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
