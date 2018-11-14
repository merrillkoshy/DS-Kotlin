package diplomatssummit.com.diplomatssummit.invest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import diplomatssummit.com.diplomatssummit.R

class OpportunityActivity : AppCompatActivity() {
var regionsWithImageUrls=String()
var regionsPlusUrl: ArrayList<String>? = null
var regionList: ArrayList<String>? = null
var urlsList: ArrayList<String>? = null
var flag=0

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


        val mediaUrl=intent.getStringArrayListExtra("mediaUrl")
        val countries=intent.getStringArrayListExtra("countries")
        val description=intent.getStringArrayListExtra("description")
        val inMedia=intent.getStringArrayListExtra("inMedia")


        var a=0
        while(a<countries.size){
            if(country==countries[a]) {
                flag = a
                break
            }
            a++
        }

        Log.d("index:",""+flag)
        Log.d("mediaUrl:",mediaUrl[flag])
        Log.d("Countries:",countries[flag])
        Log.d("Description:",description[flag])
        Log.d("inMedia:",inMedia[flag])


        regionsWithImageUrls=mediaUrl[flag]


        //Region-wise split(blobLevel)
        val re1=regionsWithImageUrls.split(')')
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




        val heading:TextView=findViewById(R.id.heading)
        heading.setText(country)


        //RecycleView
        val rv:RecyclerView = findViewById(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        val adapter = OpportunityAdapter(this,regionList,urlsList,country,description[flag],inMedia[flag])
        rv.adapter = adapter
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


