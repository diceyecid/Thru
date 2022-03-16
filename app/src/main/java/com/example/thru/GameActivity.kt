package com.example.thru

import GameEngine
import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import com.example.thru.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityGameBinding
    private lateinit var view : SurfaceView
    private lateinit var engine : GameEngine

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // initialization
        Util.hideSystemBars( window )
        binding = ActivityGameBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        engine = GameEngine( this )
        view = GameView( this, engine )
        setContentView( view )

        // event listeners
        binding.canvas.setOnClickListener{ reverseSquare() }
        binding.canvas.setOnLongClickListener{ pauseSquare() }
    }


    /********** player control **********/


    // reverse square movement
    private fun reverseSquare()
    {
        Log.d( "onClick", "working" )
    }

    // pause square movement
    private fun pauseSquare(): Boolean
    {
        Log.d( "onLongClick", "working" )

        return true
    }


    /********** game loop **********/


    // move wall from top to down
    private fun moveWall()
    {

    }

    // move square from left to right
    private fun moveSquare()
    {

    }

    // check if wall and square collide
    private fun checkCollision()
    {

    }
}