import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.thru.R
import kotlin.random.Random

private const val HEIGHT : Int = 150
private const val INIT_Y : Int = HEIGHT * -1

class Wall(
    private val context : Context,
    private val emptyOffset : Int,
    private val emptyWidth : Int,
    private val speed : Int
    )
{
    // shape
    val leftWall : Rect
    val rightWall : Rect

    // physic
    var top : Int = INIT_Y
        get() = leftWall.top
        private set

    // render
    private var paint : Paint

    init
    {
        // randomize the empty slot position
        val randomWidth = Random.nextInt( emptyOffset, Util.screenWidth - emptyOffset - emptyWidth - 1 )

        // shape
        leftWall = Rect( 0, INIT_Y, randomWidth, INIT_Y + HEIGHT )
        rightWall = Rect( randomWidth + emptyWidth, INIT_Y, Util.screenWidth, INIT_Y + HEIGHT )

        // render
        paint = Paint()
        paint.color = ContextCompat.getColor( context, R.color.primary_red )
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f

    }

    fun update()
    {
        // move
        leftWall.offset( 0, speed )
        rightWall.offset( 0, speed )
    }

    fun draw( canvas : Canvas )
    {
        canvas.drawRect( leftWall, paint )
        canvas.drawRect( rightWall, paint )
    }

    /********** lgoic **********/


}
