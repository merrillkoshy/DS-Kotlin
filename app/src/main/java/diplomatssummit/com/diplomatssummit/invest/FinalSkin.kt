package diplomatssummit.com.diplomatssummit.invest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.databases.CtyInvMethod

class FinalSkin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_skin)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val ims=intent.getStringExtra("inMedia")

        Log.d("imsFS",ims)

        val region=intent.getStringExtra("RegionName")
        title=region


        val imsSplit1=ims.split("}")






        val regionurl=intent.getStringExtra("RegionUrl")
        val country=intent.getStringExtra("country")
        val position=intent.getIntExtra("position",0)
        val regionDescr=intent.getStringExtra("description")



        val splitContentinit=regionDescr.split('}')
        val splitContent=splitContentinit[position].split('{')
        Log.d("regionNamefromSplit",splitContent[0])
        Log.d("splitContent",splitContent[1])
        val iv:ImageView=findViewById(R.id.finalSkinImage)
        val tv:TextView=findViewById(R.id.finalSkinContent)
        val posthead:TextView=findViewById(R.id.postheading)
        Picasso.get().load(regionurl).resize(400,200).centerCrop().placeholder( R.drawable.progress_animation ).into(iv)

        val fab:FloatingActionButton=findViewById(R.id.fab_finalskin)
        fab.setOnClickListener{
            val intent:Intent=Intent(this,FinalsknImages::class.java)

            intent.putExtra("region",region)
            intent.putExtra("country",country)
            intent.putExtra("inMedia",ims)
            startActivity(intent)

        }

        val postheading=splitContent[1].split('^')[0]
        val postExtract=splitContent[1].split('^')[1].replace("u201c","\"").replace("u201d","\"").replace("u2018","\'").replace("u2019","\'").replace("u2013",":").replace("  ","\n")
        Log.d("postHeading",postheading)
        Log.d("postExtract",postExtract)

            posthead.setText(postheading)
            tv.setText(postExtract)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun populateRegionDescription(region: String): MutableList<String> {
        val ctob= CtyInvMethod()
        val inDescr=ctob.readDescriptionFromTitle(region)
        val size=inDescr.size
        val imlist:MutableList<String>
        imlist= arrayListOf()
        var i=0
        while(i<size)
        {
            var imageBlob= inDescr[i].InDescription!!
            Log.d("FinalRegionDescrTest",imageBlob)
            imageBlob.let { imlist.add(i,it) }
            i++
        }
        return imlist
    }
}
