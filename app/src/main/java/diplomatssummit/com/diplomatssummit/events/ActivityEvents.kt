package diplomatssummit.com.diplomatssummit.events

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import diplomatssummit.com.diplomatssummit.BetaActivity
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.Indicators.IndefinitePagerIndicator
import diplomatssummit.com.diplomatssummit.app_ui.PartnerRCAdapter
import diplomatssummit.com.diplomatssummit.app_ui.SampleActivity
import diplomatssummit.com.diplomatssummit.databases.PeMethods
import diplomatssummit.com.diplomatssummit.databases.SampleMethods
import kotlinx.android.synthetic.main.activity_events.*
import okhttp3.*
import java.io.IOException
import diplomatssummit.com.diplomatssummit.MainActivity
import diplomatssummit.com.diplomatssummit.app_ui.ExternalEventsAdapter


class ActivityEvents:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initiateWidgets()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }



    fun initiateWidgets(){
        setContentView(R.layout.activity_events)
        imageviews()
        rvlists()
        indicators()


        title="Business and Trade Events"
    }
    fun imageviews(){
        val upcoming:TextView=findViewById(R.id.upcomingIv)
        upcoming.text="UPCOMING EVENTS"
        val past:TextView=findViewById(R.id.pastIv)
        past.text="PAST EVENTS"
        val ttimes:TextView=findViewById(R.id.t_times_iv)
        ttimes.text="Events in Dubai"
    }
    fun rvlists(){
        val upcData=intent.getStringArrayListExtra("upcoming")
        val upcomingAdapter:PartnerRCAdapter= PartnerRCAdapter(this,upcData)
        val upcoming:RecyclerView=findViewById(R.id.upcomingRv)
        upcoming.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        upcoming.adapter=upcomingAdapter

        val data=intent.getStringArrayListExtra("datadoc")
        val pastAdapter:PartnerRCAdapter= PartnerRCAdapter(this,data)
        val past:RecyclerView=findViewById(R.id.pastRv)
        past.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        past.adapter=pastAdapter

        val extEvents=intent.getStringArrayListExtra("extEvents")[0]
        val ext_Events=extEvents.split("*")
        val external_events=ArrayList<String>()
        var i=1
        while(i<ext_Events.size)
        {
            external_events.add(ext_Events[i])
            Log.d("ext_Events",ext_Events[i])
            i++
        }
        val ttimesAdapter= ExternalEventsAdapter(external_events)
        val ttimes:RecyclerView=findViewById(R.id.t_times_rv)
        ttimes.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        ttimes.adapter=ttimesAdapter
    }
    fun indicators(){
        val upcoming:IndefinitePagerIndicator=findViewById(R.id.upcomingIndicator)
        upcoming.attachToRecyclerView(upcomingRv)
        val past:IndefinitePagerIndicator=findViewById(R.id.pastIndicator)
        past.attachToRecyclerView(pastRv)
        val ttimes:IndefinitePagerIndicator=findViewById(R.id.t_times_indicator)
        ttimes.attachToRecyclerView(t_times_rv)
    }



    fun ttimeslist():MutableList<String>{
        val sampleObj = PeMethods()
        val ar= sampleObj.readMediaRowsBasedOnType(1)
        val size=sampleObj.itemsize()
        var imagelist= arrayListOf<String>()
        var i=0

        while (i<size) {
            var imagePath = ar[i].MediaUrl
            imagePath?.let { imagelist.add(i, it) }
            i++
        }
        return imagelist
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}