import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.thru.R

private const val MAX_SLOT_WIDTH : Int = 800
private const val SQUARE_WIDTH : Int = 50

class Square( private val context : Context )
{
    // shape
    private val slot : Rect
    private val square : Rect

    // physic
    private var speed : Int

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

        // physics
        speed = 5

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
        // move square
        square.offset( speed, 0 )

        // if reached the boundary of slot, reverse direction
        if( !slot.contains( square ) )
        {
            speed *= -1
            square.offset( speed, 0 )
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
}