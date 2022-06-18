package com.example.weather

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.fragments.MainFragment
import org.json.JSONObject

const val API_KEY = "cac2ced2c87c4e2c98594133220706"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, MainFragment.newInstance())
            .commit()

        getResult("Minsk")
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/forecast" +
                ".json?key=$API_KEY&q=$name&days=5&aqi=no&alerts=no"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val obj = JSONObject(response)
                Log.d("MyLog", "all: $obj")
                val current = obj.getJSONObject("current")
                val location = obj.getJSONObject("location")

                // город
                val tvCity: TextView = findViewById(R.id.tvSity)
                tvCity.text = name
                Log.d("MyLog", "sity: $name")

                // текущая температура
                val currentTemp = current.getString("temp_c")
                val tvCurrentTemp : TextView = findViewById(R.id.tvCurrentTemp)
                tvCurrentTemp.text = currentTemp
                Log.d("MyLog", "temp: $currentTemp")

                // локальное время
                val time = location.getString("localtime")
                val tvDate: TextView = findViewById(R.id.tvDate)
                tvDate.text = time
                Log.d("MyLog", "time: $time")

                // санни
                val condition = current.getJSONObject("condition")
                val sunny = condition.getString("text")
                val tvSunny: TextView = findViewById(R.id.tvSunny)
                tvSunny.text = sunny
                Log.d("MyLog", "pupa: $sunny")

                // мин/макс темпа
                val forecast = obj.getJSONObject("forecast")
                Log.d("MyLog", "forecast: $forecast")
                val forecastDay = forecast.getJSONArray("forecastday")
                Log.d("MyLog", "forecast: $forecastDay")
                //val day = forecast.getJSONObject("day")
                //Log.d("MyLog", "forecast: $day")
                /*val maxTemp = day.getString("maxtemp_c")
                Log.d("MyLog", "forecast: $maxTemp")
                val minTemp = day.getString("mintemp_c")
                Log.d("MyLog", "forecast: $minTemp")*/

                // картинка санни
                val imSunny = condition.getString("icon")
                val imWeather: ImageView = findViewById(R.id.imWeather)
                val imUri: Uri = ("https:$imSunny").toUri()
                imWeather.setImageURI(imUri)
            },
            {
                Log.d("MyLog", "Volley error: $it")
            }
        )

        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }
}