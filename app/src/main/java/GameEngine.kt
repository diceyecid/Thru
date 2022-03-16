import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.Log
import java.util.*

class GameEngine( private val context : Context )
{
    // game objects
    private val wallsBefore: Queue<Wall> = LinkedList<Wall>()
    private val wallsAfter: Queue<Wall> = LinkedList<Wall>()

    // timer
    private var frameCount: Int = 90


    // update objects for each frame
    fun update()
    {

        generateWall()
        wallsBefore.forEach{ w -> w.update() }
        detectCollision()
        wallsAfter.forEach{ w -> w.update() }
        destroyWall()

        Log.d( "engine", "wallsBefore.size = ${wallsBefore.size}" )
        Log.d( "engine", "wallsAfter.size = ${wallsAfter.size}" )

        frameCount++
    }

    // render objects for each frame
    fun draw( canvas: Canvas )
    {
        wallsBefore.forEach{ w -> w.draw( canvas ) }
        wallsAfter.forEach{ w -> w.draw( canvas ) }
    }


    /********** logics **********/


    // generate wall every 90 frames
    private fun generateWall()
    {
        if( frameCount == 90 )
        {
            wallsBefore.add( Wall( context, 200 ) )
            frameCount = 0
        }
    }

    private fun detectCollision()
    {
        val front = wallsBefore.peek()
        if( front != null && front.top >= Util.screenHeight - 500 )
            wallsAfter.add( wallsBefore.remove() )
    }

    // destroy wall if it's out of screen
    private fun destroyWall()
    {
        val front = wallsAfter.peek()
        if( front != null && front.top > Util.screenHeight )
            wallsAfter.remove()
    }
}