package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityPhoneBinding

class PhoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)






        binding.phoneBtm.setOnClickListener {

            val number = binding.phoneInput.text.toString()

            if (number.isNotEmpty()) {

                val uri = Uri.parse("tel:$number")

                val dialIntent = Intent(Intent.ACTION_DIAL, uri)

                startActivity(dialIntent)
            }
        }




        binding.mapBtm.setOnClickListener {

            val query = binding.mapInput.text.toString()

            if (query.isNotEmpty()) {

                val uri = Uri.Builder()
                    .scheme("geo")
                    .opaquePart("0,0")
                    .appendQueryParameter("q", query)
                    .build()

                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")

                startActivity(intent)
            }
        }


        binding.googleBtm.setOnClickListener {

            val query = binding.googleInput.text.toString()

            if (query.isNotEmpty()) {

                // Link do wyszukiwania Google
                val uri = Uri.parse("https://www.google.com/search?q=" + Uri.encode(query))

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }



    }

    /*fun call(view: View) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + "8344814819")
        startActivity(dialIntent)
    }*/
}