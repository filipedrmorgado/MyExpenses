package com.filipemorgado.myexpenses.ui.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.filipemorgado.myexpenses.model.DateRange
import com.filipemorgado.myexpenses.utilities.TAG_HOME_VIEW_MODEL

class HomeViewModel : ViewModel() {

    // Keeps the current date range up-to-date
    private val _dateRange = MutableLiveData(DateRange.TODAY)
    val dateRange: LiveData<DateRange> = _dateRange


    /**
     * Updates the current selected date range to display the transactions
     */
    fun updateDateRange(dateRange: DateRange) {
        Log.i(TAG_HOME_VIEW_MODEL, "updateDateRange: dateRange=$dateRange")
        _dateRange.value = dateRange
    }
}