package diplomatssummit.com.diplomatssummit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.SeekBar
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import diplomatssummit.com.diplomatssummit.app_ui.AnimManager
import diplomatssummit.com.diplomatssummit.app_ui.GalleryRecyclerView
import diplomatssummit.com.diplomatssummit.app_ui.PartnerRCAdapter
import diplomatssummit.com.diplomatssummit.databases.PartnerMethod
import diplomatssummit.com.diplomatssummit.R.id.recyclerView
import android.support.v7.widget.PagerSnapHelper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import diplomatssummit.com.diplomatssummit.R.id.recyclerView
import diplomatssummit.com.diplomatssummit.app_ui.Indicators.IndefinitePagerIndicator
import diplomatssummit.com.diplomatssummit.app_ui.stories.Viewstory
import diplomatssummit.com.diplomatssummit.databases.AchievementsMethod
import kotlinx.android.synthetic.main.partner_rv.*


class PartnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.partner_rv)
        title="Partners"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initializeWidgets()
    }

    private fun initializeWidgets() {

        try {

            val partnerNames=intent.getStringExtra("partnerNames")
            val partnerThumbs=intent.getStringExtra("partnerThumbs")

            val achieveThumb=intent.getStringExtra("achieveThumb")
            val achieveSD=intent.getStringExtra("achieveSD")
            val achieveLD=intent.getStringExtra("achieveLD")

            Log.d("Names",partnerNames)
            Log.d("Thumbs",partnerThumbs)
            val getDatas=partnerThumbs.split(",")


            val mRecyclerView: GalleryRecyclerView =findViewById(R.id.rv_partnerlist)
            val recyclerAdapter: PartnerRCAdapter =PartnerRCAdapter(
                    this,
                    getDatas

            )
            val itemClickListener:GalleryRecyclerView.OnItemClickListener

            mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            mRecyclerView.adapter=recyclerAdapter
            val indicator:IndefinitePagerIndicator=findViewById(R.id.recyclerview_pager_indicator)
            indicator.attachToRecyclerView(mRecyclerView)


            mRecyclerView
                    // set scroll speed（pixel/s）
                    .initFlingSpeed(9000)
                    // set page distance and visible distance of the nearby.
                    .initPageParams(0, 40)
                    // set the animation factor
                    .setAnimFactor(0.1f)
                    // set animation type. you can choose AnimManager.ANIM_BOTTOM_TO_TOP or AnimManager.ANIM_TOP_TO_BOTTOM
                    .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)

                    // set default position
                    .initPosition(0)
                    // finally call method
                    .setUp()



            val animleft:Animation=AnimationUtils.loadAnimation(this,R.anim.slide_left)
            val achieveIv:ImageView=findViewById(R.id.achievements_icon)
            val achieveTv:TextView=findViewById(R.id.achievements)
            achieveIv.startAnimation(animleft)
            achieveTv.startAnimation(animleft)
            associations_icon.startAnimation(animleft)
            associations.startAnimation(animleft)

            val getAchievementsImage=achieveThumb.split(",")
            val getAchievementsDescriptionShort=achieveSD.split("$")
            val getAchievementsDescriptionLong=achieveLD.split("$")
            val achieveRv: GalleryRecyclerView =findViewById(R.id.rv_achievelist)
            val rcyad2: Viewstory = Viewstory(
                    this,
                    getAchievementsImage,getAchievementsDescriptionShort,getAchievementsDescriptionLong
            )


            val indicator2:IndefinitePagerIndicator=findViewById(R.id.rv_achieveIndicator)

            achieveRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            achieveRv.adapter=rcyad2

            indicator2.attachToRecyclerView(achieveRv)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    fun getAchievementsImage(): MutableList<String> {

        val ob=AchievementsMethod()
        val array=ob.readAllMedias()
        val size=ob.itemsize()
        var imagelist:MutableList<String>

        imagelist= arrayListOf()

        var i=0

        while (i<size) {

            var ip2=array[i].Thumb
            ip2?.let { imagelist.add(i, it) }
            i++
        }
        return imagelist
    }

    fun getAchievementsDescriptionShort(): MutableList<String> {

        val ob=AchievementsMethod()
        val array=ob.readAllMedias()
        val size=ob.itemsize()
        var ldlist:MutableList<String>

        ldlist= arrayListOf()

        var i=0

        while (i<size) {
            var ip2=array[i].Shortdesc
            ip2?.let { ldlist.add(i, it) }
            i++
        }
        return ldlist
    }

    fun getAchievementsDescriptionLong(): MutableList<String> {

        val ob=AchievementsMethod()
        val array=ob.readAllMedias()
        val size=ob.itemsize()
        var ldlist:MutableList<String>

        ldlist= arrayListOf()

        var i=0

        while (i<size) {
            var ip2=array[i].Longdesc
            ip2?.let { ldlist.add(i, it) }
            i++
        }
        return ldlist
    }


}
