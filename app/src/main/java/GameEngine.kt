import android.content.Context
import android.graphics.Canvas
import java.util.*

private const val SPEED : Int = 5

class GameEngine( private val context : Context )
{
    // game objects
    private val square : Square = Square( context, SPEED )
    private val wallsBefore: Queue<Wall> = LinkedList()
    private val wallsAfter: Queue<Wall> = LinkedList()

    // wall generation
    private val emptyWidth : Int = 200
    private var frameCount: Int = ( square.slotWidth - emptyWidth ) / 5

    // update objects for each frame
    fun update()
    {
        // move existing objects
        square.update()
        wallsBefore.forEach{ w -> w.update() }
        wallsAfter.forEach{ w -> w.update() }

        // logic on walls
        generateWall()
        detectCollision()
        destroyWall()

        frameCount++
    }

    // render objects for each frame
    fun draw( canvas: Canvas )
    {
        square.draw( canvas )
        wallsBefore.forEach{ w -> w.draw( canvas ) }
        wallsAfter.forEach{ w -> w.draw( canvas ) }
    }


    /********** logics **********/


    // generate wall every 90 frames
    private fun generateWall()
    {
        if( frameCount == ( square.slotWidth - emptyWidth ) / 5 )
        {
            wallsBefore.add( Wall( context, ( Util.screenWidth - square.slotWidth ) / 2, emptyWidth , SPEED ) )
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