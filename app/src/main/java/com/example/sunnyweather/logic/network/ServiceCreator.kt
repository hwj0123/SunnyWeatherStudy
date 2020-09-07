package com.example.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//Retrofit构建器
object ServiceCreator {

    private const val BASE_URL="https://api.caiyunapp.com/"

    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //通过这一行代码，可以使接口动态代理对象创建简化为
    //val appService=ServiceCreator.create(AppService::class.java)
    fun <T> create(serviceClass: Class<T>):T= retrofit.create(serviceClass)

    //通过这行代码，可以在上面的基础上进一步简化为
    //val appService=ServiceCreator.create<AppService>()
    inline fun <reified T> create():T=create(T::class.java)
}