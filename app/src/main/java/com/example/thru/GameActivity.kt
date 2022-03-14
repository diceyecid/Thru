package com.example.thru

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.thru.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Util.hideSystemBars( window )

        binding.score.paint.style = Paint.Style.STROKE
        binding.score.paint.strokeWidth = 5f
    }
}