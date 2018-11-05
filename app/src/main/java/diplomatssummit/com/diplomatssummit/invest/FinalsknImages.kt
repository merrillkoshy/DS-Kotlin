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

        title = ""

        imagelist=getImsfromTitle(country,region)



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

    fun getImsfromTitle(country: String,region:String): ArrayList<String> {
        val ob=CtyInvMethod()
        val fromTableAL=ob.readImagesFromTitle(country)
        var size=fromTableAL.size
        var i=0
        var returningArrayList:ArrayList<String>
        returningArrayList= arrayListOf()
        var finalArrayList:ArrayList<String>
        = arrayListOf()

        var imageStringLine=String()

        while(i<size)
        {
            var buffer=fromTableAL[i].InMedia
            buffer?.let { returningArrayList.add(it) }
            i++
        }
        val stage1filter=returningArrayList[0].split('}')
        val stage1size=stage1filter.size-1

        var j=0
        val stage2filter:ArrayList<String>
                =arrayListOf()
        while(j<stage1size){
            var buffer=stage1filter[j]
           buffer.let {stage2filter.add(it) }
            if (buffer.contains(region)){
                imageStringLine=buffer.split('{')[1]
            }
            j++
        }
        val buffer=imageStringLine.split(',')
        val sizeStringline=buffer.size

        var k=0
        while(k<sizeStringline)
        {
            var wordbuffer=buffer[k]
            wordbuffer.let { finalArrayList.add(it) }
            Log.d("fromTableAL", wordbuffer)
            k++
        }
        return finalArrayList
    }

}
