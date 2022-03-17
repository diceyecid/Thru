package com.example.thru

import GameEngine
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.WindowInsets

class GameActivity : AppCompatActivity()
{
    private lateinit var view : SurfaceView
    private lateinit var engine : GameEngine

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // initialize system related info
        Util.hideSystemBars( window )
        Util.screenWidth = getScreenWidth()
        Util.screenHeight = getScreenHeight()

        // initialize game
        engine = GameEngine( this )
        view = GameView( this, engine )
        setContentView( view )
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