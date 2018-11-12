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

    private var mSeekbar: SeekBar? = null
    private var listener: gallery.OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeWidgets()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun initializeWidgets(){
        setContentView(R.layout.gallery_rv)
        val mRecyclerView: GalleryRecyclerView =findViewById(R.id.rv_list)
        val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(
                this,
                getDatas(),
                getTitles()
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


        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                mSeekbar?.setProgress(mRecyclerView.getScrolledPosition());
            }
        })

        mSeekbar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mRecyclerView.smoothScrollToPosition(progress);
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })



        // TODO: Rename method, update argument and hook method into UI event
        fun onButtonPressed(uri: Uri) {
            listener?.onFragmentInteraction(uri)
        }

    }

    fun getDatas(): MutableList<String>? {

        val ob2= GtableMethods()
        val ar2=ob2.readMediaRowsBasedOnType(1)
        val s2=ob2.itemsize()
        var imagelist:MutableList<String>
        var titlelist:MutableList<String>
        imagelist= arrayListOf()
        titlelist= arrayListOf()
        var i=0

        while (i<s2) {
            var ip2=ar2[i].GalleryThumb
            var title=ar2[i].Title
            title?.let { titlelist.add(i,it) }
            ip2!!.replace("-225x300","").let { imagelist.add(i, it) }
            Log.d("testGal",ip2)

            i++
        }


        return imagelist
    }



    fun getTitles(): MutableList<String>? {

        val ob2= GtableMethods()
        val ar2=ob2.readMediaRowsBasedOnType(1)
        val s2=ob2.itemsize()
        var titlelist:MutableList<String>
        titlelist= arrayListOf()
        var i=0

        while (i<s2) {
            var ip2=ar2[i].GalleryThumb
            var title=ar2[i].Title
            title?.let { titlelist.add(i,it) }
            Log.d("testGal",ip2)
            i++
        }
        return titlelist
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}