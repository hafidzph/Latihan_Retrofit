package com.exercise.retrofitexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.retrofitexercise.adapter.FilmAdapter
import com.exercise.retrofitexercise.adapter.NewsAdapter
import com.exercise.retrofitexercise.databinding.ActivityFilmBinding
import com.exercise.retrofitexercise.model.ResponseDataFilmItem
import com.exercise.retrofitexercise.model.ResponseDataNewsItem
import com.exercise.retrofitexercise.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmActivity : AppCompatActivity() {
    lateinit var binding: ActivityFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFilm()
    }

    fun getDataFilm(){
        RetrofitClient.instance.getAllFilm().enqueue(object : Callback<List<ResponseDataFilmItem>> {
            override fun onResponse(
                call: Call<List<ResponseDataFilmItem>>,
                response: Response<List<ResponseDataFilmItem>>
            ) {
                if(response.isSuccessful){
                    binding.rvClient.layoutManager = LinearLayoutManager(this@FilmActivity,
                        LinearLayoutManager.VERTICAL,
                        false)
                    binding.rvClient.adapter = FilmAdapter(response.body()!!)
                }else{
                    Toast.makeText(this@FilmActivity, "Failed Load Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                Toast.makeText(this@FilmActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}