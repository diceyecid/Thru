import android.content.Context
import android.graphics.Canvas
import java.util.*

class GameEngine( private val context : Context )
{
    // game objects
    private val wallsBefore : Queue<Wall> = LinkedList<Wall>()
    private val wallsAfter : Queue<Wall> = LinkedList<Wall>()

    // timer
    private var frameCount : Int = 90


    // update objects for each frame
    fun update()
    {
        generateWall()
        wallsBefore.forEach{ w -> w.update() }
        detectCollision()
        destroyWall()
        frameCount++
    }

    // render objects for each frame
    fun draw( canvas: Canvas )
    {
        wallsBefore.forEach{ w -> w.draw( canvas ) }
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


    }

    // destroy wall if it's out of screen
    private fun destroyWall()
    {


    }
}