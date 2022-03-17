import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.thru.R

private const val MAX_SLOT_WIDTH : Int = 800
private const val SQUARE_WIDTH : Int = 50

class Square( private val context : Context, private var speed : Int )
{
    // shape
    private val slot : Rect
    val square : Rect

    // physic
    private var isMoving : Boolean
    var bottom : Int
        get() = square.bottom
        private set

    // render
    private var slotPaint : Paint
    private var squarePaint : Paint

    // getters
    val slotWidth get() = slot.width()

    init
    {
        // determine slot and square positions
        val slotWidth = Math.min( MAX_SLOT_WIDTH, Util.screenWidth - SQUARE_WIDTH * 3 )
        val slotPosX = ( Util.screenWidth - slotWidth ) / 2
        val squarePosX = ( Util.screenWidth - SQUARE_WIDTH ) / 2
        val posY = Util.screenHeight * 4 / 5

        Log.d( "screenWidth", Util.screenWidth.toString() )
        Log.d( "slotWidth", slotWidth.toString() )

        // shape
        slot = Rect(
            slotPosX,
            posY,
            slotPosX + slotWidth,
            posY + SQUARE_WIDTH
        )
        square = Rect(
            squarePosX,
            posY,
            squarePosX + SQUARE_WIDTH,
            posY + SQUARE_WIDTH
        )

        // physic
        isMoving = true
        bottom = square.bottom

        // render
        slotPaint = Paint()
        slotPaint.color = ContextCompat.getColor( context, R.color.secondary_blue )
        slotPaint.style = Paint.Style.STROKE
        slotPaint.strokeWidth = 10f

        squarePaint = Paint()
        squarePaint.color = ContextCompat.getColor( context, R.color.primary_blue )
        squarePaint.style = Paint.Style.FILL
        squarePaint.strokeWidth = 0f
    }

    fun update()
    {
        if( isMoving )
        {
            // move square
            square.offset( speed, 0 )

            // if reached the boundary of slot, reverse direction
            if( !slot.contains( square ) )
            {
                reverseDir()
                square.offset( speed * 2, 0 )
            }
        }
    }

    fun draw( canvas : Canvas )
    {
        // offset stroke to be the outside of slot
        val slotBorder = Rect(
            slot.left - ( slotPaint.strokeWidth / 2 ).toInt(),
            slot.top - ( slotPaint.strokeWidth / 2 ).toInt(),
            slot.right + ( slotPaint.strokeWidth / 2 ).toInt(),
            slot.bottom + ( slotPaint.strokeWidth / 2 ).toInt(),
        )

        canvas.drawRect( slotBorder, slotPaint )
        canvas.drawRect( square, squarePaint )
    }


    /********** logics **********/


    fun increaseSpeed()
    {
        speed++
    }

    fun reverseDir()
    {
        speed *= -1
    }

    fun pause()
    {
        isMoving = false
    }

    fun resume()
    {
        isMoving = true
    }
}