package com.example.fish_prediction_221351052

import android.annotation.SuppressLint
import android.content.res.AssetFileDescriptor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.io.IOException // Import IOException

class SimulasiActivity : AppCompatActivity() {

    private lateinit var tflite: Interpreter
    private lateinit var inputSpecies: EditText
    private lateinit var inputLength1: EditText
    private lateinit var inputLength2: EditText
    private lateinit var inputLength3: EditText
    private lateinit var inputHeight: EditText
    private lateinit var inputWidth: EditText
    private lateinit var inputFeature7: EditText
    private lateinit var btnPrediksi: Button
    private lateinit var outputPrediksi: TextView
    private lateinit var backButton: ImageButton

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulasi)

        inputSpecies = findViewById(R.id.inputSpecies)
        inputLength1 = findViewById(R.id.inputLength1)
        inputLength2 = findViewById(R.id.inputLength2)
        inputLength3 = findViewById(R.id.inputLength3)
        inputHeight = findViewById(R.id.inputHeight)
        inputWidth = findViewById(R.id.inputWidth)
        inputFeature7 = findViewById(R.id.inputFeature7)
        btnPrediksi = findViewById(R.id.btnPrediksi)
        outputPrediksi = findViewById(R.id.outputPrediksi)
        backButton = findViewById(R.id.backButton)

        try {
            tflite = Interpreter(loadModelFile())
        } catch (e: Exception) {
            Log.e("SimulasiActivity", "Error loading TFLite model", e)
            Toast.makeText(this, "Gagal memuat model TFLite. Aplikasi mungkin tidak berfungsi dengan benar.", Toast.LENGTH_LONG).show()

        }

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnPrediksi.setOnClickListener {

            val species = inputSpecies.text.toString().toFloatOrNull()
            val length1 = inputLength1.text.toString().toFloatOrNull()
            val length2 = inputLength2.text.toString().toFloatOrNull()
            val length3 = inputLength3.text.toString().toFloatOrNull()
            val height = inputHeight.text.toString().toFloatOrNull()
            val width = inputWidth.text.toString().toFloatOrNull()
            val feature7Val = inputFeature7.text.toString().toFloatOrNull() // Ambil nilai input ke-7


            if (species == null || length1 == null || length2 == null ||
                length3 == null || height == null || width == null || feature7Val == null) {
                Toast.makeText(this, "Semua 7 field harus diisi dengan angka yang valid!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            val inputValues = floatArrayOf(
                species,
                length1,
                length2,
                length3,
                height,
                width,
                feature7Val
            )


            val inputBuffer = ByteBuffer.allocateDirect(7 * 4).order(ByteOrder.nativeOrder())
            inputBuffer.rewind()
            for (value in inputValues) {
                inputBuffer.putFloat(value)
            }

            val outputBuffer = ByteBuffer.allocateDirect(1 * 4).order(ByteOrder.nativeOrder())
            outputBuffer.rewind()

            try {
                if (::tflite.isInitialized) {
                    tflite.run(inputBuffer, outputBuffer)

                    outputBuffer.rewind()
                    val prediction = outputBuffer.float

                    outputPrediksi.text = "Hasil Prediksi Berat Ikan: %.2f gram".format(prediction)
                } else {
                    Toast.makeText(this, "Model TFLite belum siap.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("SimulasiActivity", "Error running TFLite inference", e)
                Toast.makeText(this, "Terjadi kesalahan saat prediksi: ${e.message}", Toast.LENGTH_LONG).show()
                outputPrediksi.text = "Error: Prediksi gagal."
            }
        }
    }

    @Throws(IOException::class)
    private fun loadModelFile(): ByteBuffer {
        val fileDescriptor: AssetFileDescriptor = assets.openFd("fish_weight_prediction.tflite") // GANTI DENGAN NAMA FILE MODEL ANDA
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel: FileChannel = inputStream.channel
        val startOffset: Long = fileDescriptor.startOffset
        val declaredLength: Long = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::tflite.isInitialized) {
            tflite.close()
        }
    }
}