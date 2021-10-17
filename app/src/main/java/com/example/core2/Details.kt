package com.example.core2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class Details : AppCompatActivity() {
    lateinit var location: Location
    private var ratingValue : Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        location = intent.getParcelableExtra<Location>("location") as Location

        val detailName = findViewById<TextView>(R.id.textDetail)
        val detailAuth = findViewById<TextView>(R.id.textDetailAuth)
        val rating = findViewById<RatingBar>(R.id.detailRatingBar)

        location?.let {
            detailName.text = it.name
            detailAuth.text = it.name
            rating.rating = it.rating.toString().toFloatOrNull()!!
        }

        rating.setOnRatingBarChangeListener { rating, starRating, a ->
            Toast.makeText(this, "The rating given :${starRating}", Toast.LENGTH_SHORT).show()
            ratingValue = starRating // Keep the data in a variable to keep the star rating
        }
    }

    override fun onBackPressed() {
        val latestData = Location(location.name, location.auth, ratingValue)

        val dataStore = intent.apply {
            this.putExtra("locations", latestData)
        }
        setResult(Activity.RESULT_OK, dataStore)

        super.onBackPressed()
    }

}

