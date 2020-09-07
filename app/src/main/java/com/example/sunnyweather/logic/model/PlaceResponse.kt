package com.example.sunnyweather.logic.model

import android.location.Location
import com.google.gson.annotations.SerializedName
//由于json中一些字段的命名可能与Kotlin的命名规范不一致，这里使用@SerializedName注解来让json字段
//与Kotlin字段之间建立映射关系
data class Place(val name:String,val location:Location
                 ,@SerializedName("formatted_address")val address:String)

data class Location(val lng:String,val lat:String)

data class PlaceResponse(val status:String,val places:List<Place>)