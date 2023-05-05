package com.exercise.retrofitexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.exercise.retrofitexercise.databinding.ActivityUpdateNewsBinding
import com.exercise.retrofitexercise.viewmodel.NewsViewModel

class UpdateNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            val bundle = intent.extras
            val id = bundle?.getInt("id")
            updateNews(id!!)
        }
    }

    fun updateNews(id: Int){
        val vmUpdateNews = ViewModelProvider(this)[NewsViewModel::class.java]
        vmUpdateNews.putNews(id, binding.title.text.toString(), binding.img.text.toString(),
            binding.descNews.text.toString(), binding.authorNews.text.toString())
        vmUpdateNews.updateNews.observe(this){
                Toast.makeText(this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
        }
    }
}