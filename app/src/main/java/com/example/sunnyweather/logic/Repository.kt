package com.example.sunnyweather.logic

import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException

object Repository {



    fun searchPlaces(query:String)= liveData(Dispatchers.IO) {
        //liveData()函数可以自动构建并且返回一个LiveData对象，然后在它的代码块中提供一个挂起函数的上下文，这样就可以调用任意的挂起函数
        //这里调用了挂起函数searchPlaces()来查询城市的数据
        //线程参数指定为Dispatchers.IO，那么代码块中的代码都运行在子线程中
        val result=try{
            //搜索城市数据，判断响应状态，根据状态来进行不同的处理
            val placeResponse=SunnyWeatherNetwork.searchPlaces(query)
            if(placeResponse.status=="ok"){
                val places=placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        //使用emit()方法将包装的结果发射出去
        emit(result)
    }
}