package com.example.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//统一的网络数据源访问入口，对所有网络请求的API进行封装
object SunnyWeatherNetwork {

    private val placeService=ServiceCreator.create<PlaceService>()


    suspend fun searchPlaces(query:String)= placeService.searchPlaces(query).await()

    /**
     *  将await()函数声明为Call<T>的拓展函数，这样所有返回值为Call<T>类型的Retrofit接口就可以直接调用await()
     */
    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body=response.body()
                    if(body!=null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException(""))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }

    }
}