package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication :Application(){
    companion object{
        //将context设置成静态变量很容易产生内存泄漏的问题，因此使用注解来让AS忽略这个警告提示
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        //彩云天气的令牌值
        const val TOKEN=""
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}