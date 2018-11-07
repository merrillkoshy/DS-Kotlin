package diplomatssummit.com.diplomatssummit.app_ui


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.widget.Toast
import diplomatssummit.com.diplomatssummit.R



class OnSwipeTouchListener : AppCompatActivity(), OnGestureListener {
    internal var gestureDetector: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.swipetest)
        gestureDetector = GestureDetector(this, this)
    }

    override fun onFling(motionEvent1: MotionEvent, motionEvent2: MotionEvent, X: Float, Y: Float): Boolean {
        if (motionEvent1.y - motionEvent2.y > 50) {
            Toast.makeText(this, "You Swiped up!", Toast.LENGTH_LONG).show()
            return true
        }

        if (motionEvent2.y - motionEvent1.y > 50) {
            Toast.makeText(this, "You Swiped Down!", Toast.LENGTH_LONG).show()
            return true
        }

        if (motionEvent1.x - motionEvent2.x > 50) {
            Toast.makeText(this, "You Swiped Left!", Toast.LENGTH_LONG).show()
            return true
        }

        if (motionEvent2.x - motionEvent1.x > 50) {
            Toast.makeText(this, "You Swiped Right!", Toast.LENGTH_LONG).show()
            return true
        } else {
            return true
        }
    }

    override fun onLongPress(arg0: MotionEvent) {
        // TODO Auto-generated method stub
    }

    override fun onScroll(arg0: MotionEvent, arg1: MotionEvent, arg2: Float, arg3: Float): Boolean {
        // TODO Auto-generated method stub
        return false
    }

    override fun onShowPress(arg0: MotionEvent) {
        // TODO Auto-generated method stub
    }

    override fun onSingleTapUp(arg0: MotionEvent): Boolean {
        // TODO Auto-generated method stub
        return false
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        // TODO Auto-generated method stub
        return gestureDetector!!.onTouchEvent(motionEvent)
    }

    override fun onDown(arg0: MotionEvent): Boolean {
        // TODO Auto-generated method stub
        return false
    }

}