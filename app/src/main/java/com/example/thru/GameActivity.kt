package com.example.thru

import GameEngine
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout

class GameActivity : AppCompatActivity()
{
    private lateinit var frame: FrameLayout
    private lateinit var engine : GameEngine
    private lateinit var view : SurfaceView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // initialize system related info
        Util.hideSystemBars( window )
        Util.screenWidth = getScreenWidth()
        Util.screenHeight = getScreenHeight()

        // create frame
        frame = FrameLayout( this )
        frame.id = View.generateViewId()
        setContentView( frame )

        // initialize game
        engine = GameEngine( this )
        view = GameView( this, engine )
        frame.addView( view )
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


    /********** touch event handlers **********/


    override fun onTouchEvent(event: MotionEvent): Boolean
    {
        return if( !engine.isGameOver )
        {
            when( event.action )
            {
                MotionEvent.ACTION_DOWN -> {
                    engine.pauseSquare()
                    true
                }
                MotionEvent.ACTION_UP -> {
                    engine.reverseSquare()
                    engine.resumeSquare()
                    true
                }
                else -> super.onTouchEvent( event )
            }
        }
        else super.onTouchEvent( event )
    }


    /********** logic **********/


    fun gameOver( newScore : Int )
    {
        // compare and set high score
        val sharedPref = getSharedPreferences( "thru", Context.MODE_PRIVATE )
        var highScore = sharedPref.getInt( "high_score", 0 )
        if( newScore > highScore )
        {
            highScore = newScore
            with( sharedPref.edit() )
            {
                putInt( "high_score", newScore )
                apply()
            }
        }

        // display game over fragment
        val frag = GameOverFragment.newInstance( newScore, highScore )
        supportFragmentManager.beginTransaction().add( frame.id, frag, "gameOver" ).commit()
    }
}