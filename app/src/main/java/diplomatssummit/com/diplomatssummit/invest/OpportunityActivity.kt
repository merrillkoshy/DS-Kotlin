package diplomatssummit.com.diplomatssummit.invest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.databases.CtyInvMethod

class OpportunityActivity : AppCompatActivity() {
var regionsWithImageUrls: MutableList<String>? = null
var regionsPlusUrl: ArrayList<String>? = null
var regionList: ArrayList<String>? = null
var urlsList: ArrayList<String>? = null


    private var buffer: List<String>?=null
    private var buffer2: List<String>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opportunity)
        val country=intent.getStringExtra("Country")
        title=country
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        Log.d("oA1",country)

        regionsWithImageUrls=populateContent(country)


        //Region-wise split(blobLevel)
        val re1=regionsWithImageUrls!![0].split(')')
        val size= re1.size-1
        var i=0
        regionsPlusUrl= arrayListOf()
        regionList= arrayListOf()
        urlsList= arrayListOf()
        while(i<size) {
           regionsPlusUrl!!.add(re1[i])
            i++
        }


        //Region-wise split(dataLevel)
        val s2= regionsPlusUrl!!.size
        var j=0

        while (j<s2)
        {
            buffer = regionsPlusUrl!![j].split('(')
            regionList!!.add(j,buffer!![0])
            urlsList!!.add(j,buffer!![1])
            j++
        }

        //Region and urlblob split
        val rsize=regionList!!.size
        val usize=urlsList!!.size

        /*var k=0

        while(k<usize)
        {
            buffer2 = urlsList!![k].split(',')
            Log.d("oA2", buffer2!![0])
            k++
        }*/


        /*Log.d("oA3", regionList!![k])
        Log.d("oA4", urlsList!![k])*/


        val heading:TextView=findViewById(R.id.heading)
        heading.setText(country)


        //RecycleView
        val rv:RecyclerView = findViewById(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        val adapter = OpportunityAdapter(this,regionList,urlsList,country)
        rv.adapter = adapter
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

fun populateContent(country:String): MutableList<String> {
    val ciMethod=CtyInvMethod()
    val ciLocal=ciMethod.readContentFromTitle(country)
    val size=ciLocal.size
    val imlist:MutableList<String>
    imlist= arrayListOf()
    var i=0
    while(i<size)
    {
        val imageBlob= ciLocal[i].MediaUrl!!
        imageBlob.let { imlist.add(i,it) }
        i++
    }
    return imlist
}


