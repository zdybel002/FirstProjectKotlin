package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAllIntentsBinding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.listOfUsers.PersonActivity
import com.example.myapplication.mainComponets.CounterActivity
import com.example.myapplication.mainComponets.ImageActivity
import com.example.myapplication.mainComponets.IntenseActivity
import com.example.myapplication.mainComponets.SearchActivity


class AllIntentsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAllIntentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllIntentsBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userName = intent.getStringExtra("userName")

        binding.userWelkomeTW.text = "${getString(R.string.userWelcomeText)}," +
                " ${userName.toString().uppercase()}!"


        binding.counterBtm.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CounterActivity::class.java
                )
            )
        }

        binding.imageBtm.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ImageActivity::class.java
                )
            )
        }

        binding.intenseBtm.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    IntenseActivity::class.java
                )
            )
        }

        binding.searchBtm.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SearchActivity::class.java
                )
            )
        }

        binding.listUserBtm.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PersonActivity::class.java
                )
            )
        }




    }
}