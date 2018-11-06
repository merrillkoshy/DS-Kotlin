package diplomatssummit.com.diplomatssummit.articles

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.GalleryPagerAdapter
import diplomatssummit.com.diplomatssummit.app_ui.Indicators.IndefinitePagerIndicator

import diplomatssummit.com.diplomatssummit.databases.ArticlesMethod


class ArticleReaderActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = "President's Articles"

        initializeWidgets()
    }

    fun initializeWidgets(){
        setContentView(R.layout.articles_vpager)


        val viewpager: ViewPager =findViewById(R.id.inside_pager)
        val pagerAdapter = ArticleAdapter(this, getImage(),getPreviewtext(),getTeasertext())
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
}