package com.example.thru

import GameEngine
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.core.view.GestureDetectorCompat

class GameActivity : AppCompatActivity(), GestureDetector.OnGestureListener
{
    private lateinit var frame: FrameLayout
    private lateinit var engine : GameEngine
    private lateinit var view : SurfaceView
    private lateinit var gestureDetector : GestureDetectorCompat
    private var isLongClick : Boolean = false

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
        gestureDetector = GestureDetectorCompat( this, this )
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
        if( isLongClick && event.action == MotionEvent.ACTION_UP )
        {
            engine.resumeSquare()
            isLongClick = false
        }

        return if( gestureDetector.onTouchEvent( event ) ) true else super.onTouchEvent(event)
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean
    {
        engine.reverseSquare()
        return true
    }

    override fun onLongPress(p0: MotionEvent?)
    {
        isLongClick = true
        engine.pauseSquare()
    }

    override fun onDown(p0: MotionEvent?): Boolean { return true }
    override fun onShowPress(p0: MotionEvent?) {}
    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float) : Boolean { return true }
    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean { return true }


    /********** logic **********/


    fun gameOver( newScore : Int )
    {
        // compare and set high score
        val sharedPref = getPreferences( Context.MODE_PRIVATE ) ?: return
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