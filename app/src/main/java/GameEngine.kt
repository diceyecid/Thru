import android.graphics.Canvas
import android.graphics.Rect
import com.example.thru.GameActivity
import java.util.*

private const val INIT_SPEED : Int = 5
private const val EMPTY_WIDTH_TOL : Int = 20
private const val MAX_EMPTY_WIDTH : Int = 200 - EMPTY_WIDTH_TOL
private const val MIN_EMPTY_WIDTH : Int = 60

private const val INCREASE_SPEED : Int = 20
private const val SHRINK_EMPTY : Int = 10

class GameEngine( private val activity : GameActivity )
{
    // game objects
    private val square : Square = Square( activity, INIT_SPEED )
    private val wallsBefore: Queue<Wall> = LinkedList()
    private val wallsAfter: Queue<Wall> = LinkedList()
    private val score : Score = Score( activity )

    // wall generation
    private var speed : Int = INIT_SPEED
    private var freq : Int = square.slotWidth / speed
    private var frameCount : Int = freq
    private var emptyWidth : Int = MAX_EMPTY_WIDTH

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
        if( frameCount == freq )
        {
            // randomize empty width within tolerance
            val width = Random().nextInt( EMPTY_WIDTH_TOL ) + emptyWidth

            wallsBefore.add( Wall( activity, ( Util.screenWidth - square.slotWidth ) / 2, width, speed ) )
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

    // gradually increase difficulty
    private fun increaseDifficulty()
    {
        // add difficulty by speeding up
        if( score.value % INCREASE_SPEED == 0 )
        {
            speed++
            freq = square.slotWidth / speed
            square.increaseSpeed()
        }
        // add difficulty by shrinking empty space between walls
        else if ( score.value % SHRINK_EMPTY == 0 && emptyWidth > MIN_EMPTY_WIDTH )
        {
            emptyWidth -= 10
        }
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