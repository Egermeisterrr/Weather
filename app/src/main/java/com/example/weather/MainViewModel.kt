package com.example.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.DayItem

class MainViewModel : ViewModel() {
    val liveData = MutableLiveData<DayItem>()
    val liveDataList = MutableLiveData<List<DayItem>>()
}