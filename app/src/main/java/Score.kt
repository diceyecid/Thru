import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.example.thru.R

class Score( private val context : Context )
{
    var value : Int = 0

    // position
    private var posX : Float = Util.screenWidth / 2f
    private var posY : Float = 150f

    // style
    private val size : Float = 100f
    private val stroke : Float = 10f

    fun draw( canvas : Canvas )
    {
        // text config
        val paint = Paint()
        paint.textSize = size
        paint.textAlign = Paint.Align.CENTER
        paint.typeface = Typeface.create( Typeface.DEFAULT, Typeface.BOLD )

        // draw stroke
        paint.color = ContextCompat.getColor( context, R.color.background )
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = stroke
        canvas.drawText( value.toString(), posX, posY, paint )

        // draw text
        paint.color = ContextCompat.getColor( context, R.color.text_dark )
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f
        canvas.drawText( value.toString(), posX, posY, paint )
    }

    fun add()
    {
        value++
    }
}