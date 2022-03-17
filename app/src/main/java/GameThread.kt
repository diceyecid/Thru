import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.SurfaceHolder
import androidx.core.content.ContextCompat
import com.example.thru.R

private const val DELAY : Long = 4;

class GameThread(
    private val surfaceHolder: SurfaceHolder,
    private val context: Context,
    private val engine : GameEngine
    ) : Thread()
{
    public var isRunning : Boolean = true
    private val paint : Paint = Paint()

    init
    {
        paint.color = ContextCompat.getColor( context, R.color.background )
    }

    override fun run()
    {
        super.run()

        while( isRunning )
        {
            // check if game is over
            if( engine.isGameOver )
            {
                isRunning = false
                break
            }

            // update game logic
            engine.update()

            // draw canvas
            val canvas = surfaceHolder.lockCanvas()
            if( canvas != null )
            {
                synchronized( surfaceHolder )
                {
                    // draw background
                    canvas.drawRect( 0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), paint )
                    engine.draw( canvas )
                }
            }
            surfaceHolder.unlockCanvasAndPost( canvas )

            // refresh delay
            try
            {
                Thread.sleep( DELAY )
            }
            catch( ex: InterruptedException )
            {
                Log.d( "Refresh Error: ", ex.toString() )
            }
        }
    }


}