package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "cac2ced2c87c4e2c98594133220706"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bGet.setOnClickListener {
            getResult("London")
        }

        /*supportFragmentManager
            .beginTransaction()
            .replace(binding.placeHolder, MainFragment.newInstance())
            .commit()*/
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val obj = JSONObject(response)
                val current = obj.getJSONObject("current")
                val location = obj.getJSONObject("location")

                val textResource = resources.getString(R.string.weatherInLondon)
                val currentTemp = current.getString("temp_c").toInt()
                val currentTempText = String.format(textResource, currentTemp)

                // установили значение текущей температуры для TextView
                binding.tvTemp.text = currentTempText
                Log.d("MyLog", "Response: ${current.getString("temp_c")}")

                //локальное время
                val time = location.getString("localtime")

                //condition, но тут 2 штуки сразу, надо распарсить
                val condition = current.getString("condition")

                // ещё надо ссчитать всю инфу для последующих 3ёх дней
                // также нужно минимальную и максимальную темпу за день
            },
            {
                Log.d("MyLog", "Volley error: $it")
            }
        )

        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }
}