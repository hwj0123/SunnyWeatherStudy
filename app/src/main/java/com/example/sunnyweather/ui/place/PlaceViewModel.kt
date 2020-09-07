package com.example.sunnyweather.ui.place

import android.view.animation.Transformation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.Repository
import com.example.sunnyweather.logic.model.Place

class PlaceViewModel:ViewModel() {

    private val searchLiveData=MutableLiveData<String>()

    val placeList=ArrayList<Place>()
    //将Repository.searchPlaces()返回的livedata转化为一个可以供Activity观察的livedata对象
    val placeLiveData= Transformations.switchMap(searchLiveData){query->
         Repository.searchPlaces(query)
    }

    fun searchPlaces(query:String){
        searchLiveData.value=query
    }
}