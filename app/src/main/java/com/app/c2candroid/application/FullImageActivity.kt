package com.app.c2candroid.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.c2candroid.R
import com.app.c2candroid.databinding.ActivityFullImageBinding
import com.app.c2candroid.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

class FullImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val result = intent.extras?.getString("FullImage")
        Glide.with(binding.root)
            .load(result)
            .into(binding.largeImage)
    }
}