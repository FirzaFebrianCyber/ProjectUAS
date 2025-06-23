package com.example.fish_prediction_221351052

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.fish_prediction_221351052.ui.DatasetActivity
import com.example.fish_prediction_221351052.ui.FiturActivity
import com.example.fish_prediction_221351052.ui.ModelActivity
import com.example.fish_prediction_221351052.ui.SimulasiActivity
import com.example.fish_prediction_221351052.ui.TentangActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuTentang = findViewById<CardView>(R.id.menuTentang)
        val menuDataset = findViewById<CardView>(R.id.menuDataset)
        val menuFitur = findViewById<CardView>(R.id.menuFitur)
        val menuModel = findViewById<CardView>(R.id.menuModel)
        val menuSimulasi = findViewById<CardView>(R.id.menuSimulasi)

        menuTentang.setOnClickListener {
            startActivity(Intent(this, TentangActivity::class.java))
        }

        menuDataset.setOnClickListener {
            startActivity(Intent(this, DatasetActivity::class.java))
        }

        menuFitur.setOnClickListener {
            startActivity(Intent(this, FiturActivity::class.java))
        }

        menuModel.setOnClickListener {
            startActivity(Intent(this, ModelActivity::class.java))
        }

        menuSimulasi.setOnClickListener {
            startActivity(Intent(this, SimulasiActivity::class.java))
        }
    }
}
