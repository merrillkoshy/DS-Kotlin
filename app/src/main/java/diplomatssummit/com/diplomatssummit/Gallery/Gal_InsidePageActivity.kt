package diplomatssummit.com.diplomatssummit.Gallery

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.TextView
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.GalleryPagerAdapter

class Gal_InsidePageActivity : AppCompatActivity() {

    var imagelist=ArrayList<String>()
    var titleIm=String()
    var size=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery_inside_vpager)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = ""



        val buffer=intent.getStringExtra("galCon")
        Log.d("see-buffer",buffer)
        val tray=buffer.split(",")
        var w=0
        while (w<tray.size)
        {
            imagelist.add(tray[w])
            Log.d("tray",tray[w])
            w++
        }

        titleIm=intent.getStringExtra("titleGal")
        size = imagelist.size

        var i = 0
        Log.d("gSize_test", size.toString())
        while (i < size) {
            Log.d("gAct_test", imagelist[i])
            i++
        }

        val viewpager: ViewPager =findViewById(R.id.inside_pager)
        val pagerAdapter = GalleryPagerAdapter(this, imagelist)
        viewpager.adapter = pagerAdapter
        val springdot: SpringDotsIndicator =findViewById(R.id.spring_dot)
        springdot.setViewPager(viewpager)

        val tv:TextView=findViewById(R.id.tvgip)
        tv.setText(titleIm)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }






}
