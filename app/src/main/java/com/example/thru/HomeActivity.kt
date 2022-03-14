package com.example.thru

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thru.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener{
            startActivity( Intent( this, GameActivity::class.java ) )
        }
    }
}