package com.example.myapplication.listOfUsers

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPersonBinding
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class PersonActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPersonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonBinding.inflate(layoutInflater)  // layoutInflater z importu android.view (domyślny)

        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val personList = mutableListOf<Person>(
            Person("Jan Kowalski", "jankowalski@mail.com",
                R.drawable.avatar1),
            Person("Alicja Kwiatkowska", "kwiatkowska@mail.com", R.drawable.avatar2)
        )
        val recyclerView = binding.rv
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PersonAdapter(personList)
        recyclerView.adapter = adapter


        val httpClient = OkHttpClient()
        val httpRequest = Request.Builder()
            .url("https://lewandowskit.eduweb.pwste.edu.pl/prod/android/test.json")
            .build();

        httpClient.newCall(httpRequest).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        Log.d("msg", "onResponse: blad HTTP")
                    } else {
                        val body = response.body?.string()
                        val parser = NetworkParser()

                        val serverResponse = parser.getPeople(body)

                        for (result in serverResponse.people) {
                            val img = when (result.imageUrl){
                                1 -> R.drawable.avatar1
                                2 -> R.drawable.avatar2
                                else -> R.drawable.ic_launcher_foreground
                            }

                            Log.d("msg", "onResponse: ")
                            personList.add(Person(result.name, result.email, img))
                        }
                        runOnUiThread {
                            adapter.notifyDataSetChanged()
                        }
                    }

                }
            }

        } )

    }


}

class NetworkParser {
    private val gson = Gson()

    fun getPeople(json: String?) : People{
        return gson.fromJson(json, People::class.java)
    }
}