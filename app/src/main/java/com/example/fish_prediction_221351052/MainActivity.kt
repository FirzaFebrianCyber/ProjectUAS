package com.example.fish_prediction_221351052

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuTentang = findViewById<View>(R.id.menuTentang)
        val menuDataset = findViewById<View>(R.id.menuDataset)
        val menuFitur = findViewById<View>(R.id.menuFitur)
        val menuModel = findViewById<View>(R.id.menuModel)
        val menuSimulasi = findViewById<View>(R.id.menuSimulasi)


        menuTentang.findViewById<TextView>(R.id.textMenu).text = "Tentang Aplikasi"
        menuDataset.findViewById<TextView>(R.id.textMenu).text = "Dataset"
        menuFitur.findViewById<TextView>(R.id.textMenu).text = "Fitur"
        menuModel.findViewById<TextView>(R.id.textMenu).text = "Model"
        menuSimulasi.findViewById<TextView>(R.id.textMenu).text = "Simulasi"


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
