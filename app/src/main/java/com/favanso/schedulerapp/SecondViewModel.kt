package com.favanso.schedulerapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.favanso.schedulerapp.MainActivity.Companion.listItems

class SecondViewModel : ViewModel() {
    private var list: MutableLiveData<ArrayList<String>> = MutableLiveData()
    private var listEvents: ArrayList<String> = ArrayList()


    //GET
    fun getValue(): MutableLiveData<ArrayList<String>> {
        return list
    }

    fun addList() {
        listEvents.clear()
        listEvents.addAll(listItems)
        list.setValue(listEvents)
    }

}//END SECONDVIEW