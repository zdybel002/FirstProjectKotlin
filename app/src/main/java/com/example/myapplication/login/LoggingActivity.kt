package com.example.myapplication.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.AllIntentsActivity
import com.example.myapplication.databinding.ActivityLogingBinding

class LoggingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogingBinding
    private val CORRECT_PASSWORD = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLogingBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.userLoggingBtm.isEnabled = false


        binding.userLooggingEt.addTextChangedListener {
            updateButtonState()
        }


        binding.userLooggingPassword.addTextChangedListener{
            updateButtonState()
        }

        binding.userLoggingBtm.setOnClickListener {

            val userLogin = binding.userLooggingEt.text
            val userPassword = binding.userLooggingPassword.text


            if(userLogin.isNotEmpty() && userPassword.contentEquals(CORRECT_PASSWORD)){

                val intent = Intent(this, AllIntentsActivity::class.java)
                intent.putExtra("userName", userLogin.toString())
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(
                    this,
                    "Nieprawidłowy login lub hasło.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun updateButtonState() {
        val loginWypelniony = binding.userLooggingEt.text.isNotEmpty()
        val hasloWypelnione = binding.userLooggingPassword.text.isNotEmpty()

        binding.userLoggingBtm.isEnabled = loginWypelniony && hasloWypelnione
    }
}