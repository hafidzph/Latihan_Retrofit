package com.exercise.retrofitexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.exercise.retrofitexercise.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.internal.Logger.TAG
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    var binding: ActivityLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        auth = Firebase.auth

        binding?.daftar?.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding?.btnLogin?.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding?.etEmail?.text.toString(),
                binding?.etPassword?.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    
}