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
    var flag= 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery_inside_vpager)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val country=intent.getStringExtra("country")
        val region=intent.getStringExtra("region")

        val ims=intent.getStringExtra("inMedia")


        title = ""

        val split1=ims.replace("\\\n","").replace("\\\r","").split("}")
        var buffer=String()
        var split2=split1
        var i=0
        while(i<split1.size){
            Log.d(region.replace("\n","").trim().toString(),split1[i].split("{")[0].trim().toString())
            if(split1[i].split("{")[0].trim().toString()==region.replace("\n","").trim().toString()) {
                Log.d("its a match",""+i)
                flag = i
                break
            }
            i++
        }


        split2=split1[flag].split("{")[1].split(",")
        var k=0
        while(k<split2.size)
        {
            buffer=split2[k]
            Log.d("buffer",buffer)
            imagelist.add(buffer)
            k++
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
