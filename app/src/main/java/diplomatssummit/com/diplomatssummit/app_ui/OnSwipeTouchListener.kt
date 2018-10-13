
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class OnSwipeTouchListener : AppCompatActivity(),
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{



    override fun onDown(event: MotionEvent): Boolean {

        return true
    }

    override fun onFling(event1: MotionEvent, event2: MotionEvent,
                         velocityX: Float, velocityY: Float): Boolean {
        Log.d("FLingtest","true")
        return true
    }

    override fun onLongPress(event: MotionEvent) {

    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent,
                          distanceX: Float, distanceY: Float): Boolean {

        return true
    }

    override fun onShowPress(event: MotionEvent) {

    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {

        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {

        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {

        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {

        return true
    }
}