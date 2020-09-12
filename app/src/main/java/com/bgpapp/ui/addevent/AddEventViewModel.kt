package com.bgpapp.ui.addevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AddEventViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val localization = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val typeOfMeeting = MutableLiveData<String>()
    val avaibleGames = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val hour = MutableLiveData<String>()

    fun updateDateOfBirth(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        date.value = calendar.time.toString()
    }

    fun updateHour(hourOfDay: Int, minute: Int) {
        hour.value = "$hourOfDay:$minute"
    }
}