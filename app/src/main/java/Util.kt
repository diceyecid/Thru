import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

// helper functions
object Util
{
    fun hideSystemBars( window: Window )
    {
        val windowInsetsController = ViewCompat.getWindowInsetsController( window.decorView ) ?: return
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide( WindowInsetsCompat.Type.systemBars() )
    }
}
