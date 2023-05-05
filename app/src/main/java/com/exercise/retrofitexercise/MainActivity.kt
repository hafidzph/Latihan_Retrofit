package com.exercise.retrofitexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.retrofitexercise.adapter.NewsAdapter
import com.exercise.retrofitexercise.databinding.ActivityMainBinding
import com.exercise.retrofitexercise.model.ResponseDataNewsItem
import com.exercise.retrofitexercise.network.RetrofitClient
import com.exercise.retrofitexercise.viewmodel.NewsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        getDataNews()

        binding.fabAddNews.setOnClickListener { startActivity(Intent(this, AddNewsActivity::class.java)) }
    }

    fun getDataNews(){
        val vmNews = ViewModelProvider(this)[NewsViewModel::class.java]
        vmNews.showAPI()
        vmNews.news.observe(this) { news ->
            try {
                if (news != null) {
                    binding.rvClient.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.rvClient.adapter = NewsAdapter(news)
                }
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
            }
        }
    }
}