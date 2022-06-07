package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        binding.bGet.setOnClickListener{
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
            {
                    response ->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                binding.tvTemp.text = "Temperature in London: ${temp.getString("temp_c")} C"
                Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
            },
            {
                Log.d("MyLog", "Volley error: $it")
            }
        )

        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }
}