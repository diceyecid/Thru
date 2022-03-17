import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.example.thru.GameActivity
import com.example.thru.R
import java.util.*

private const val SPEED : Int = 5

class GameEngine( private val activity : GameActivity )
{
    // game objects
    private val square : Square = Square( activity, SPEED )
    private val wallsBefore: Queue<Wall> = LinkedList()
    private val wallsAfter: Queue<Wall> = LinkedList()
    private val score : Score = Score( activity )

    // wall generation
    private val emptyWidth : Int = 200
    private var frameCount: Int = square.slotWidth / SPEED

    // state
    var isGameOver : Boolean = false
        private  set

    // update objects for each frame
    fun update()
    {
        // move existing objects
        square.update()
        wallsBefore.forEach{ w -> w.update() }
        wallsAfter.forEach{ w -> w.update() }

        // game logic
        generateWall()
        detectCollision()
        increaseScore()
        destroyWall()

        frameCount++
    }

    // render objects for each frame
    fun draw( canvas: Canvas )
    {
        square.draw( canvas )
        wallsBefore.forEach{ w -> w.draw( canvas ) }
        wallsAfter.forEach{ w -> w.draw( canvas ) }
        score.draw( canvas )
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

    // if the wall and square collided, game over
    private fun detectCollision()
    {
        val front = wallsBefore.peek()
        if( front != null && ( Rect.intersects( front.leftWall, square.square ) || Rect.intersects( front.rightWall, square.square ) ) )
        {
            isGameOver = true
            activity.gameOver( score.value )
        }
    }

    // if the wall passed the square, place it in the after queue and add score
    private fun increaseScore()
    {
        val front = wallsBefore.peek()
        if( front != null && front.top >= square.bottom )
        {
            wallsAfter.add( wallsBefore.remove() )
            score.add()
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