package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImageActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var btnChange: Button
    private lateinit var sizeSeek: SeekBar
    private lateinit var alphaSeek: SeekBar

    private val icons = listOf(
        R.drawable.icon1,
        R.drawable.icon2,
        R.drawable.icon3,
        R.drawable.icon4,
        R.drawable.icon5,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_image)

        // przypisanie widoków
        image = findViewById(R.id.gun)
        btnChange = findViewById(R.id.zmien_b)
        sizeSeek = findViewById(R.id.size_bar)
        alphaSeek = findViewById(R.id.przezroczystosc_Bar)

        // ustawienie początkowej ikony
        image.setImageResource(icons.random())

        // SeekBar przezroczystości
        alphaSeek.max = 255
        alphaSeek.progress = 255
        alphaSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                image.imageAlpha = progress
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        // SeekBar rozmiaru
        sizeSeek.max = 300
        sizeSeek.progress = 100
        sizeSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val scale = resources.displayMetrics.density
                val newSizePx = ((progress + 50) * scale).toInt() // minimalny rozmiar 50dp
                val lp = image.layoutParams
                lp.width = newSizePx
                lp.height = newSizePx
                image.layoutParams = lp
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        // przycisk zmieniający ikonę
        btnChange.setOnClickListener {
            image.setImageResource(icons.random())
        }
    }
}

