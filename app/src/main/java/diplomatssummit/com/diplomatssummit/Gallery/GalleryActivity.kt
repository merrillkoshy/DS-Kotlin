package diplomatssummit.com.diplomatssummit.Gallery

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.SeekBar
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.AnimManager
import diplomatssummit.com.diplomatssummit.app_ui.GalleryRecyclerView
import diplomatssummit.com.diplomatssummit.app_ui.Indicators.IndefinitePagerIndicator
import diplomatssummit.com.diplomatssummit.app_ui.RecyclerAdapter
import diplomatssummit.com.diplomatssummit.databases.GtableMethods

class GalleryActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeWidgets()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun initializeWidgets(){
        setContentView(R.layout.gallery_rv)
        val mRecyclerView: GalleryRecyclerView =findViewById(R.id.rv_list)

        val getDatas=intent.getStringArrayListExtra("galleryThumb")
        val getTitles=intent.getStringArrayListExtra("galleryTitle")
        val getMediaUrl=intent.getStringArrayListExtra("galleryMediaUrl")


        Log.d("galleryThumb",getDatas[0])
        Log.d("galleryTitle",getTitles[0])

        var i=0
        while(i<getMediaUrl.size)
        {
            Log.d("getMediaUrl",getMediaUrl[i])
            i++
        }


        val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(
                this,
                getDatas,
                getTitles,
                getMediaUrl
        )
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.adapter=recyclerAdapter
        val indicator2: IndefinitePagerIndicator =findViewById(R.id.rv_Indicator)
        indicator2.attachToRecyclerView(mRecyclerView)
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
                .setUp();


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}