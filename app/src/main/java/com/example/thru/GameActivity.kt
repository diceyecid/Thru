package com.example.thru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.thru.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // initialization
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Util.hideSystemBars( window )

        // event listeners
        binding.canvas.setOnClickListener{ reverseSquareDirection() }
        binding.canvas.setOnLongClickListener{ pauseSquareMovement() }
    }

    override fun onResume()
    {
        super.onResume()


    }

    private fun reverseSquareDirection()
    {
        Log.d( "onClick", "working" )
    }

    private fun pauseSquareMovement(): Boolean
    {
        Log.d( "onLongClick", "working" )

        return true
    }
}