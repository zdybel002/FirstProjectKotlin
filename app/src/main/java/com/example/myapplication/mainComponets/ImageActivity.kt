package com.example.myapplication.mainComponets

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

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

        // ------------------------------
        //  PRZEZROCZYSTOŚĆ
        // ------------------------------
        alphaSeek.max = 255
        alphaSeek.progress = 255
        alphaSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                image.imageAlpha = progress
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        // ------------------------------
        //  POWIĘKSZANIE OBRAZU (0.5x – 1.5x)
        // ------------------------------
        sizeSeek.max = 100       // zakres dla wygody
        sizeSeek.progress = 50   // start = 1.0x
        sizeSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {

                // skala od 0.5 do 1.5
                val scale = 0.5f + (progress / 100f)   // min 0.5, max 1.5

                // skaluje obraz, NIE zmienia wielkości ImageView
                image.scaleX = scale
                image.scaleY = scale
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        // ------------------------------
        //  ZMIANA IKONY
        // ------------------------------
        btnChange.setOnClickListener {
            image.setImageResource(icons.random())
        }
    }
}