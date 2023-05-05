package com.exercise.retrofitexercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.retrofitexercise.model.DataNews
import com.exercise.retrofitexercise.model.ResponseNews
import com.exercise.retrofitexercise.model.ResponseDataNewsItem
import com.exercise.retrofitexercise.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {
    private val _news = MutableLiveData<List<ResponseDataNewsItem>>()
    val news: LiveData<List<ResponseDataNewsItem>> = _news

    private val _addNews = MutableLiveData<ResponseNews>()
    val addNews : LiveData<ResponseNews> = _addNews

    private val _updateNews = MutableLiveData<List<ResponseNews>>()
    val updateNews : LiveData<List<ResponseNews>> = _updateNews

    private val _getNewsDetail = MutableLiveData<ResponseDataNewsItem>()
    val getNewsDetail: LiveData<ResponseDataNewsItem> = _getNewsDetail

    fun showAPI(){
        RetrofitClient.instance.getAllNews().enqueue(object : Callback<List<ResponseDataNewsItem>> {
            override fun onResponse(
                call: Call<List<ResponseDataNewsItem>>,
                response: Response<List<ResponseDataNewsItem>>
            ) {
                if(response.isSuccessful){
                    _news.postValue(response.body())
                }else{
                    _news.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                _news.postValue(null)
            }
        })
    }

    fun postNews(title: String, img: String, desc: String, author: String){
        RetrofitClient.instance.postDataNews(DataNews(title, img, desc, author)).enqueue(object : Callback<ResponseNews> {
            override fun onResponse(
                call: Call<ResponseNews>,
                response: Response<ResponseNews>
            ) {
                if(response.isSuccessful){
                    _addNews.postValue(response.body())
                }else{
                    _addNews.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                _addNews.postValue(null)
            }
        })
    }

    fun putNews(id: Int, title: String, img: String, desc: String, author: String){
        RetrofitClient.instance.putDataNews(id, DataNews(title, img, desc, author)).enqueue(object : Callback<List<ResponseNews>> {
            override fun onResponse(
                call: Call<List<ResponseNews>>,
                response: Response<List<ResponseNews>>
            ) {
                if(response.isSuccessful){
                    _updateNews.postValue(response.body())
                }else{
                    _updateNews.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseNews>>, t: Throwable) {
                _updateNews.postValue(null)
            }
        })
    }

    fun getNewsDetail(id: Int){
        RetrofitClient.instance.getNewsById(id).enqueue(object : Callback<ResponseDataNewsItem> {
            override fun onResponse(
                call: Call<ResponseDataNewsItem>,
                response: Response<ResponseDataNewsItem>
            ) {
                if(response.isSuccessful){
                    _getNewsDetail.postValue(response.body())
                }else{
                    _getNewsDetail.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDataNewsItem>, t: Throwable) {
                _getNewsDetail.postValue(null)
            }
        })
    }
}