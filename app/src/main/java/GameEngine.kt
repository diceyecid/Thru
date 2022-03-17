import android.content.Context
import android.graphics.Canvas
import android.util.Log
import java.util.*

class GameEngine( private val context : Context )
{
    // game objects
    private val wallsBefore: Queue<Wall> = LinkedList()
    private val wallsAfter: Queue<Wall> = LinkedList()
    private val square : Square = Square( context )

    // timer
    private var frameCount: Int = 90

    init
    {
        square.slotWidth
    }


    // update objects for each frame
    fun update()
    {
        // move existing objects
        wallsBefore.forEach{ w -> w.update() }
        wallsAfter.forEach{ w -> w.update() }
        square.update()

        // logic on walls
        generateWall()
        detectCollision()
        destroyWall()

        frameCount++
    }

    // render objects for each frame
    fun draw( canvas: Canvas )
    {
        wallsBefore.forEach{ w -> w.draw( canvas ) }
        wallsAfter.forEach{ w -> w.draw( canvas ) }
        square.draw( canvas )
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
        // TODO: actually perform collision detection between walls and square
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