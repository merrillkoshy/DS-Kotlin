package diplomatssummit.com.diplomatssummit.homepage

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.Gallery.GalleryActivity
import diplomatssummit.com.diplomatssummit.Gallery.gallery
import diplomatssummit.com.diplomatssummit.PartnerActivity
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.articles.ArticleReaderActivity
import diplomatssummit.com.diplomatssummit.events.ActivityEvents
import diplomatssummit.com.diplomatssummit.invest.InvestActivity
import okhttp3.*
import java.io.IOException

class HomeActivity:AppCompatActivity(){
    private var pastTarget = String()
    private var upcTarget = String()
    private var invTarget = String()
    private var resultArray = ArrayList<String>()
    private var resultArrayUpc = ArrayList<String>()
    private var resultArrayInvest = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeWidgets()
        fetchJsonUpcoming()
        fetchJsonPast()
        fetchJsonInvest()
    }

    fun fetchJsonInvest() {
        val url = "https://diplomatssummit.com/volley/investtable.php"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                //Slicing the response
                invTarget = body.toString()

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun fetchJsonPast() {
        val url = "https://diplomatssummit.com/volley/pastevents.php"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                //Slicing the response
                pastTarget = body.toString()

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun fetchJsonUpcoming() {
        val url = "https://diplomatssummit.com/volley/upcomingevents.php"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                //Slicing the response
                upcTarget = body.toString()

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun initializeWidgets(){
        setContentView(R.layout.fragment_home)
        val invButton: ImageView =findViewById(R.id.inv)
        val timel: ImageView =findViewById(R.id.tradeevents)
        val galbutton: ImageView =findViewById(R.id.gal)
        val bp: ImageView =findViewById(R.id.bp)
        val dp: ImageView =findViewById(R.id.dp)
        val art: ImageView =findViewById(R.id.art)

        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/events.png").centerCrop().resize(1348, 555).onlyScaleDown().into(timel)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/inv.png").centerCrop().resize(1000, 700).onlyScaleDown().into(invButton)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/gallery.png").centerCrop().resize(600, 400).onlyScaleDown().into(galbutton)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/bp.jpg").centerCrop().resize(1224, 717).onlyScaleDown().into(bp)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/art.jpg").centerCrop().resize(600, 400).onlyScaleDown().into(art)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/dp.png").centerCrop().resize(1364, 298).onlyScaleDown().into(dp)

        invButton.setOnClickListener{
            invTarget = invTarget.replace("[", "").replace("]", "")
            val splitinvimg=splitTextInv(invTarget)

            val intent=Intent(this,InvestActivity::class.java)
            intent.putExtra("imageurls",splitinvimg)

            startActivity(intent)

        }



        timel.setOnClickListener{


            pastTarget = pastTarget.replace("[", "").replace("]", "")
            val splitted=splitText(pastTarget)

            upcTarget = upcTarget.replace("[", "").replace("]", "")
            val splittedupc=splitTextUpc(upcTarget)

            val intent=Intent(this,ActivityEvents::class.java)
            intent.putExtra("datadoc",splitted)
            intent.putExtra("upcoming",splittedupc)

            startActivity(intent)

        }



        galbutton.setOnClickListener(){
            val intent: Intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }
        bp.setOnClickListener(){
            val intent: Intent = Intent(this, PartnerActivity::class.java)
            startActivity(intent)
        }
        art.setOnClickListener(){
            val intent: Intent = Intent(this, ArticleReaderActivity::class.java)
            startActivity(intent)
        }
    }

    fun splitText(targetText: String): ArrayList<String> {

        var targetArray = targetText.split(",")
        val size = targetArray.size
        var i = 0
        while (i < size) {
            val buffer = targetArray[i].replace("\"", "").replace("\\", "").replace(" ", "")
            resultArray.add(buffer)
            i++
        }
        val result_size = resultArray.size
        var k = 0
        while (k < result_size) {
            Log.d("RA", resultArray[k])
            k++
        }
        return resultArray

    }

    fun splitTextUpc(targetText: String): ArrayList<String> {

        var targetArray = targetText.split(",")
        val size = targetArray.size
        var i = 0
        while (i < size) {
            val buffer = targetArray[i].replace("\"", "").replace("\\", "").replace(" ", "")
            resultArrayUpc.add(buffer)
            i++
        }
        val result_size = resultArrayUpc.size
        var k = 0
        while (k < result_size) {
            Log.d("RA-UPC", resultArrayUpc[k])
            k++
        }
        return resultArrayUpc

    }

    fun splitTextInv(targetText: String): ArrayList<String> {

        var targetArray = targetText.split(",")
        val size = targetArray.size
        var i = 0
        while (i < size) {
            val buffer = targetArray[i].replace("\"", "").replace("\\", "").replace(" ", "")
            resultArrayInvest.add(buffer)
            i++
        }
        val result_size = resultArrayInvest.size
        var k = 0
        while (k < result_size) {
            Log.d("RA-INV", resultArrayInvest[k])
            k++
        }
        return resultArrayInvest

    }


}