package com.example.sunnyweather.ui.place

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunnyweather.R
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment:Fragment() {

    //懒加载获取PlaceViewModel的实例，允许我们在这个类中随时使用viewmodel这个变量，不用关心它何时初始化，是否为空
    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //加载fragment_place布局
        return inflater.inflate(R.layout.fragment_place,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager=LinearLayoutManager(activity)
        recyclerView.layoutManager=layoutManager
        //使用placeList作为数据源
        adapter= PlaceAdapter(this,viewModel.placeList)
        recyclerView.adapter=adapter
        //监听搜索框中的内容变化，当内容发生变化时，就根据新的地址发布搜索请求
        searchPlaceEdit.addTextChangedListener{editable->
            val content=editable.toString()
            if(content.isNotEmpty()){
                viewModel.searchPlaces(content)
            }else{
                recyclerView.visibility=View.GONE
                bgImageView.visibility=View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        //获取服务器响应的数据，对数据进行相应的判断
        viewModel.placeLiveData.observe(this, Observer {result->
            val places=result.getOrNull()
            if(places!=null){
                recyclerView.visibility=View.VISIBLE
                bgImageView.visibility=View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查询到指定地址",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }



}