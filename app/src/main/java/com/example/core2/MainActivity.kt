package com.example.core2

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {
    lateinit var uni: Location
    lateinit var station: Location
    lateinit var hall: Location
    lateinit var park: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataSeeding()

        val intent = Intent(this, Details::class.java)

        val uniClick = findViewById<ImageView>(R.id.imageUni)
        val stationClick = findViewById<ImageView>(R.id.imageStation)
        val hallClick = findViewById<ImageView>(R.id.imageHall)
        val parkClick = findViewById<ImageView>(R.id.imagePark)

        uniClick.setOnClickListener {
            intent.putExtra("locations", uni)
            newActivityResult.launch(intent)
        }

        stationClick.setOnClickListener {
            intent.putExtra("locations", station)
            newActivityResult.launch(intent)
        }

        hallClick.setOnClickListener {
            intent.putExtra("locations", hall)
            newActivityResult.launch(intent)
        }

        parkClick.setOnClickListener {
            intent.putExtra("locations", park)
            newActivityResult.launch(intent)
        }
        Log.i("MainActivity", "It is working")

    }

    private val newActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                // Declaring Variables for the rating on the main activity
                val textUni = findViewById<TextView>(R.id.ratingUni)
                val textStation = findViewById<TextView>(R.id.ratingStation)
                val textHall = findViewById<TextView>(R.id.ratingHall)
                val textPark = findViewById<TextView>(R.id.ratingPark)

                // Transfer data to get from the previous intent
                val data = result.data

                // New variable to keep the data
                val keepData = data?.getParcelableExtra<Location>("locations") as Location
                keepData?.let {
                    if (keepData.name == "University") {
                        textUni.text = keepData.rating.toString()
                        uni = keepData // Update the data parceable (THe data in the Location.kt)
                    }

                    if (keepData.name == "Station") {
                        textStation.text = keepData.rating.toString()
                        station = keepData
                    }

                    if (keepData.name == "Hall") {
                        textHall.text = keepData.rating.toString()
                        hall = keepData
                    }

                    if (keepData.name == "Park") {
                        textPark.text = keepData.rating.toString()
                        park = keepData
                    }
                }
            }

        }

    }

    private fun dataSeeding() {
        uni = Location(
            "University",
            "Swinburne University",
            1.0F
        )

        station = Location(
            "Station",
            "Swinburne University",
            2.0F
        )

        hall = Location(
            "Hall",
            "Swinburne University",
            3.0F
        )

        park = Location(
            "Park",
            "Swinburne University",
            4.0F
        )
    }
}
