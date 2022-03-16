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
private const val INIT_Y : Int = 50

class Wall(
    private val context : Context,
    private val emptyWidth : Int
    )
{
    // shape
    private var leftWall : Rect
    private var rightWall : Rect

    // physic
    var top : Int = INIT_Y
        get() = leftWall.top
    private var speed : Int

    // render
    private var paint : Paint

    init
    {
        // randomize the empty slot position
        val screenWidth = Util.screenWidth
        val randomWidth = Random.nextInt( 1, screenWidth - emptyWidth - 1 )

        // shape
        leftWall = Rect( 0, INIT_Y, randomWidth, INIT_Y + HEIGHT )
        rightWall = Rect( randomWidth + emptyWidth, INIT_Y, screenWidth, INIT_Y + HEIGHT )

        // physic
        speed = 5

        // render
        paint = Paint()
        paint.color = ContextCompat.getColor( context, R.color.primary_red )
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f

    }

    fun update()
    {
        // move
        leftWall.top += speed
        leftWall.bottom += speed
        rightWall.top += speed
        rightWall.bottom += speed
    }

    fun draw( canvas : Canvas )
    {
        canvas.drawRect( leftWall, paint )
        canvas.drawRect( rightWall, paint )
    }
}
