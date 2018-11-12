package diplomatssummit.com.diplomatssummit.invest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.SeekBar

import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.AnimManager
import diplomatssummit.com.diplomatssummit.app_ui.GalleryRecyclerView


import diplomatssummit.com.diplomatssummit.app_ui.InvAdapter

import diplomatssummit.com.diplomatssummit.databases.InvestMethod

class InvestActivity : AppCompatActivity() {
    private var mSeekbar: SeekBar? = null
    private val mRecyclerView: GalleryRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = "Investment Opportunities"



        mSeekbar =findViewById(R.id.seekBar);
        mSeekbar?.max=getDatas()!!.size-1

        val loginBtn:Button=findViewById(R.id.loginBtn)

        val mRecyclerView: GalleryRecyclerView =findViewById(R.id.rv_list)
        val getDatas=intent.getStringArrayListExtra("imageurls")
        val getCountry=intent.getStringArrayListExtra("country")
        val recyclerAdapter: InvAdapter = InvAdapter(
                this,
                getDatas,
                getCountry,
                getDescr()
        )


        loginBtn.setOnClickListener {
            val intent:Intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        val itemClickListener:GalleryRecyclerView.OnItemClickListener
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.adapter=recyclerAdapter


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

        mSeekbar?.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mRecyclerView.smoothScrollToPosition(progress);
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    fun getDatas(): MutableList<String>? {

        val ob2=InvestMethod()
        val ar2=ob2.readMediaRowsBasedOnType(1)
        val s2=ob2.itemsize()
        var imagelist:MutableList<String>
        imagelist= arrayListOf()

        var i=0

        while (i<s2) {
            var ip2=ar2[i].MediaUrl
            ip2!!.replace("-225x300","").let { imagelist.add(i, it) }
            Log.d("testGal",ip2)
            i++
        }

        return imagelist
    }



    fun getCountry(): MutableList<String>? {

        val ob2=InvestMethod()
        val ar2=ob2.readMediaRowsBasedOnType(1)
        val s2=ob2.itemsize()
        var titlelist:MutableList<String>
        titlelist= arrayListOf()
        var i=0

        while (i<s2) {
            var title=ar2[i].Country
            title?.let { titlelist.add(i,it) }
            i++
        }
        return titlelist
    }

    fun getDescr(): MutableList<String>? {

        val ob2=InvestMethod()
        val ar2=ob2.readMediaRowsBasedOnType(1)
        val s2=ob2.itemsize()
        var titlelist:MutableList<String>
        titlelist= arrayListOf()
        var i=0

        while (i<s2) {
            var title=ar2[i].InDescription
            title?.let { titlelist.add(i,it) }
            i++
        }
        return titlelist
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
