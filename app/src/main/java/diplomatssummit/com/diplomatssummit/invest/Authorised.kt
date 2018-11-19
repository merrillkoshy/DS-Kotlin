package diplomatssummit.com.diplomatssummit.invest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.*
import diplomatssummit.com.diplomatssummit.app_ui.Indicators.IndefinitePagerIndicator
import diplomatssummit.com.diplomatssummit.databases.CtyInvMethod
import diplomatssummit.com.diplomatssummit.databases.InvestMethod

class Authorised : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorised)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = "Investment Opportunities"

        val userName=intent.getStringExtra("userName")
        val passWord=intent.getStringExtra("passWord")

        val loggedInAs:TextView=findViewById(R.id.loggedInAs)
        loggedInAs.setText("Logged in DS ID: "+userName)



        val getDatas=intent.getStringArrayListExtra("imageurls")
        val getCountry=intent.getStringArrayListExtra("countries")
        val getDescr=intent.getStringArrayListExtra("description")


        val mRecyclerView:GalleryRecyclerView=findViewById(R.id.rv_list)
        val recyclerAdapter: InvAuthdapter = InvAuthdapter(
                this,
                getDatas,
                getCountry,
                getDescr
        )



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


        val indicator: IndefinitePagerIndicator =findViewById(R.id.rv_indicator)
        indicator.attachToRecyclerView(mRecyclerView)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
