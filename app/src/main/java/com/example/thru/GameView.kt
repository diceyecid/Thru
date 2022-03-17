package com.example.thru

import GameEngine
import GameThread
import android.content.Context
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(
    context: Context,
    private val engine: GameEngine
    ) : SurfaceView( context ), SurfaceHolder.Callback
{
    private var gameThread = GameThread( holder, context, engine )

    init
    {
        holder.addCallback( this )
        isFocusable = true
    }

    override fun surfaceCreated(p0: SurfaceHolder)
    {
        // start main thread of game
        if( !gameThread.isRunning )
            gameThread = GameThread( holder, context, engine )

        gameThread.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int)
    {
        // do nothing
    }

    override fun surfaceDestroyed(p0: SurfaceHolder)
    {
        gameThread.isRunning = false

        // stop thread
        var retry = true
        while( retry )
        {
            try
            {
                gameThread.join()
                retry = false
            }
            catch( ex : InterruptedException )
            {
                Log.d( "Thread Join Error: ", ex.toString() )
            }
        }
    }
}