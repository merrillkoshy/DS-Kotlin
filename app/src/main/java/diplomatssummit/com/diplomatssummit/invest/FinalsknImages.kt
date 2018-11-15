package diplomatssummit.com.diplomatssummit.invest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.TextView
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.GalleryPagerAdapter
import diplomatssummit.com.diplomatssummit.databases.CtyInvMethod

class FinalsknImages : AppCompatActivity() {

    var imagelist=ArrayList<String>()
    var titleIm=String()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery_inside_vpager)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val country=intent.getStringExtra("country")
        val region=intent.getStringExtra("region")

        val ims=intent.getStringExtra("inMedia")


        title = ""

        val split1=ims.split("}")

        var i=0
        while(i<split1.size){
            if(split1[i].split("{")[0]==region)
                imagelist=split1[i].split("{")[1].split(",") as ArrayList<String>
            i++
        }



        val viewpager: ViewPager =findViewById(R.id.inside_pager)
        val pagerAdapter = GalleryPagerAdapter(this, imagelist)
        viewpager.adapter = pagerAdapter
        val springdot: SpringDotsIndicator =findViewById(R.id.spring_dot)
        springdot.setViewPager(viewpager)

        val tv: TextView =findViewById(R.id.tvgip)
        tv.setText(titleIm)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}
