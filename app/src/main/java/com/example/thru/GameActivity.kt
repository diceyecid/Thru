package com.example.thru

import GameEngine
import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.SurfaceView
import android.view.WindowInsets
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

        // get screen width and height
        Util.screenWidth = getScreenWidth()
        Util.screenHeight = getScreenHeight()

        // event listeners
        binding.canvas.setOnClickListener{ reverseSquare() }
        binding.canvas.setOnLongClickListener{ pauseSquare() }
    }


    /********** screen width and height **********/


    private fun getScreenWidth() : Int
    {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility( WindowInsets.Type.systemBars() )
        return windowMetrics.bounds.width() - insets.left - insets.right
    }

    private fun getScreenHeight() : Int
    {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility( WindowInsets.Type.systemBars() )
        return windowMetrics.bounds.height() - insets.left - insets.right
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

}