package com.example.thru

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        Util.hideSystemBars( window )

        val sharedPref = getSharedPreferences( "thru", Context.MODE_PRIVATE )
        val highScore = sharedPref.getInt( "high_score", 0 )
        Log.d( "high", highScore.toString() )
        if( highScore != 0 )
            binding.highScore.text = highScore.toString()

        binding.playButton.setOnClickListener{
            startActivity( Intent( this, GameActivity::class.java ) )
        }
    }
}