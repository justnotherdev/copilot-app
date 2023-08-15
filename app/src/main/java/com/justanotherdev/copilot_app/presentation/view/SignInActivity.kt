package com.justanotherdev.copilot_app.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.justanotherdev.copilot_app.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)

    }

}