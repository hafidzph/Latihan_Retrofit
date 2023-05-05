package com.exercise.retrofitexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.exercise.retrofitexercise.databinding.ActivityAddNewsBinding
import com.exercise.retrofitexercise.viewmodel.NewsViewModel

class AddNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            addNews()
        }
    }

    fun addNews(){
        val vmAddNews = ViewModelProvider(this)[NewsViewModel::class.java]
        vmAddNews.postNews(binding.title.text.toString(), binding.img.text.toString(),
            binding.descNews.text.toString(), binding.authorNews.text.toString())
        vmAddNews.addNews.observe(this){
            if(it != null){
                Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}