package com.example.thru

import GameEngine
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.WindowInsets
import androidx.core.view.GestureDetectorCompat

class GameActivity : AppCompatActivity(), GestureDetector.OnGestureListener
{
    private lateinit var view : SurfaceView
    private lateinit var engine : GameEngine
    private lateinit var gestureDetector : GestureDetectorCompat
    private var isLongClick : Boolean = false

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
        gestureDetector = GestureDetectorCompat( this, this )
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
}