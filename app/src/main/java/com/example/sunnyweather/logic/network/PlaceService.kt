package com.example.sunnyweather.logic.network

import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    /***
     * 当调用searchPlaces()方法时，Retrofit会自动发送一个GET请求，去访问GET注解配置的地址
     * https://api.caiyunapp.com/v2/place?query=北京&token={token}&lang=zh_CN
     * 返回值自动解析成PlaceResponse对象
     */
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query")query:String): Call<PlaceResponse>
}