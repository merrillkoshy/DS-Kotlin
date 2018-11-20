package diplomatssummit.com.diplomatssummit.articles

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.OnSwipeTouchListener

import diplomatssummit.com.diplomatssummit.databases.ArticlesMethod
import kotlinx.android.synthetic.main.articles_vpager.*


class ArticleReaderActivity:AppCompatActivity(), GestureDetector.OnGestureListener {
    private var swipe: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = "President's Articles"

        initializeWidgets()


    }

    fun initializeWidgets(){
        setContentView(R.layout.articles_vpager)

        swipe= GestureDetector(this@ArticleReaderActivity,this@ArticleReaderActivity)

        val articlesThumb=intent.getStringExtra("articlesThumb")
        val articlesTitle=intent.getStringExtra("articlesTitle")
        val articlesSD=intent.getStringExtra("articlesSD")
        val articlesLD=intent.getStringExtra("articlesLD")


        val getImage=articlesThumb.split(",")
        val getPreviewtext=articlesTitle.split(",")
        val getTeasertext=articlesSD.split("$")
        val getFullStory=articlesLD.split("$")

        val viewpager: ViewPager =findViewById(R.id.inside_pager)
        val pagerAdapter = ArticleAdapter(this, getImage,getPreviewtext,getTeasertext,getFullStory)
        viewpager.adapter = pagerAdapter
        val springdot: SpringDotsIndicator =findViewById(R.id.spring_dot)
        springdot.setViewPager(viewpager)
    }

    fun getImage(): MutableList<String>? {
        val imageobj=ArticlesMethod()
        val imageArrayRx=imageobj.readAllMedias()
        val imageArrayRxSize=imageobj.itemsize()
        val imageArrayTx:MutableList<String>
        = arrayListOf()
        var i=0
        while(i<imageArrayRxSize)
        {
            var buffer=imageArrayRx[i].ArticleImage
            buffer?.let { imageArrayTx.add(it) }
            i++
        }
        return imageArrayTx
    }

    fun getPreviewtext(): MutableList<String>? {
        val ptobj=ArticlesMethod()
        val ptArrayRx=ptobj.readAllMedias()
        val ptArrayRxSize=ptobj.itemsize()
        val ptArrayTx:MutableList<String>
                = arrayListOf()
        var i=0
        while(i<ptArrayRxSize)
        {
            var buffer=ptArrayRx[i].ArticleHeading
            buffer?.let { ptArrayTx.add(it) }
            i++
        }
        return ptArrayTx
    }

    fun getTeasertext(): MutableList<String>? {
        val ptobj=ArticlesMethod()
        val ptArrayRx=ptobj.readAllMedias()
        val ptArrayRxSize=ptobj.itemsize()
        val ptArrayTx:MutableList<String>
                = arrayListOf()
        var i=0
        while(i<ptArrayRxSize)
        {
            var buffer=ptArrayRx[i].ArticlePreview
            buffer?.let { ptArrayTx.add(it) }
            i++
        }
        return ptArrayTx
    }

    fun getFullStory(): MutableList<String>? {
        val ptobj=ArticlesMethod()
        val ptArrayRx=ptobj.readAllMedias()
        val ptArrayRxSize=ptobj.itemsize()
        val fsArrayTx:MutableList<String>
                = arrayListOf()
        var i=0
        while(i<ptArrayRxSize)
        {
            var buffer=ptArrayRx[i].ArticleDetailed
            buffer?.let { fsArrayTx.add(it) }
            i++
        }
        return fsArrayTx
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        if (e1!!.y - e2!!.y > 50) {
            Toast.makeText(this, "You Swiped up!", Toast.LENGTH_LONG).show()
            return true
        } else {
            return true
        }
    }

    override fun onShowPress(e: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDown(e: MotionEvent?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLongPress(e: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        // TODO Auto-generated method stub
        return swipe!!.onTouchEvent(motionEvent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}