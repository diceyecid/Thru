import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import com.example.thru.GameActivity
import java.util.*

private const val SPEED : Int = 5

class GameEngine( private val activity : GameActivity )
{
    // game objects
    private val square : Square = Square( activity, SPEED )
    private val wallsBefore: Queue<Wall> = LinkedList()
    private val wallsAfter: Queue<Wall> = LinkedList()

    // wall generation
    private val emptyWidth : Int = 200
    private var frameCount: Int = square.slotWidth / SPEED

    // states
    private var score : Int = 0
    var isGameOver : Boolean = false
        private  set

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
        if( frameCount == square.slotWidth / SPEED )
        {
            wallsBefore.add( Wall( activity, ( Util.screenWidth - square.slotWidth ) / 2, emptyWidth , SPEED ) )
            frameCount = 0
        }
    }

    private fun detectCollision()
    {
        val front = wallsBefore.peek()

        if( front != null )
        {
            // if the wall and square collided, game over
            if( Rect.intersects( front.leftWall, square.square ) || Rect.intersects( front.rightWall, square.square ) )
            {
                isGameOver = true
                activity.gameOver( score )
            }

            // if the wall passed the square, place it in the after queue
            if( front.top >= square.bottom )
            {
                score++
                wallsAfter.add(wallsBefore.remove())
            }
        }

    }

    // destroy wall if it's out of screen
    private fun destroyWall()
    {
        val front = wallsAfter.peek()
        if( front != null && front.top > Util.screenHeight )
            wallsAfter.remove()
    }


    /********** player control **********/


    // reverse square movement
    fun reverseSquare()
    {
        square.reverseDir()
    }

    // pause square movement
    fun pauseSquare()
    {
        square.pause()
    }

    // resume square movement
    fun resumeSquare()
    {
        square.resume()
    }
}