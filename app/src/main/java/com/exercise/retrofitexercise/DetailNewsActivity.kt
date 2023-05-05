package com.exercise.retrofitexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.exercise.retrofitexercise.adapter.NewsAdapter
import com.exercise.retrofitexercise.databinding.ActivityDetailNewsBinding
import com.exercise.retrofitexercise.viewmodel.NewsViewModel

class DetailNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val getId = intent.getStringExtra("id")!!.toInt()

        getDetail(getId)
    }

    fun getDetail(id: Int){
        val vmNews = ViewModelProvider(this)[NewsViewModel::class.java]
        vmNews.getNewsDetail(id)
        vmNews.getNewsDetail.observe(this) {
            binding.titleTextView.text = it.title
            binding.authorTextView.text = it.author
            binding.dateTextView.text = it.createdAt
            binding.contentTextView.text = it.description
            Glide.with(this).load(it.image).into(binding.imageView)
        }
    }
}