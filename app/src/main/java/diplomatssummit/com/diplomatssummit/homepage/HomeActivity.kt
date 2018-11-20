package diplomatssummit.com.diplomatssummit.homepage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.Gallery.GalleryActivity
import diplomatssummit.com.diplomatssummit.PartnerActivity
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.articles.ArticleReaderActivity
import diplomatssummit.com.diplomatssummit.events.ActivityEvents
import diplomatssummit.com.diplomatssummit.invest.InvestActivity
import okhttp3.*
import java.io.IOException
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.support.v7.app.AlertDialog
import android.widget.Toast


class HomeActivity:AppCompatActivity(){
    private var pastTarget = String()
    private var upcTarget = String()
    private var invTarget = String()
    private var credsTarget = String()
    private var galleryTarget=String()
    private var resultArray = ArrayList<String>()
    private var countries=ArrayList<String>()
    private var indescriptions=ArrayList<String>()
    private var resultArrayUpc = ArrayList<String>()
    private var resultArrayInvest = ArrayList<String>()
    private var galleryMediaUrl = ArrayList<String>()
    private var galleryThumb = ArrayList<String>()
    private var galleryTitle = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(isOnline()) {
            initializeWidgets()
            fetchJsonUpcoming()
            fetchJsonPast()
            fetchJsonInvest()
            fetchJson_CredTable()
            fetchJsonGallery()
        }
        else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Connection Failed")

            // Display  message on alert dialog
            builder.setMessage("Diplomats Summit:The App needs an active internet connection.Press close to exit application")

            builder.setPositiveButton("CLOSE"){dialog,which ->
                Toast.makeText(this,"Closing",Toast.LENGTH_SHORT).show()
                finishAffinity()}


            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
    }

    protected fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
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

    fun fetchJsonGallery() {
        val url = "https://diplomatssummit.com/volley/galleryactivity.php"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                //Slicing the response
                galleryTarget = body.toString()

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

    fun fetchJson_CredTable() {
        val url = "https://diplomatssummit.com/volley/apploginactivity.php"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                //Slicing the response
                credsTarget = body.toString()

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

        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/events.png").centerCrop().resize(1348, 555).
                onlyScaleDown().placeholder( R.drawable.progress_animation ).
                into(timel)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/inv.png").centerCrop().resize(1000, 700).onlyScaleDown().
                placeholder( R.drawable.progress_animation ).into(invButton)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/gallery.png").centerCrop().resize(600, 400).onlyScaleDown().
                placeholder( R.drawable.progress_animation ).into(galbutton)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/bp.jpg").centerCrop().resize(1224, 717).onlyScaleDown().
                placeholder( R.drawable.progress_animation ).into(bp)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/art.jpg").centerCrop().resize(600, 400).onlyScaleDown().
                placeholder( R.drawable.progress_animation ).into(art)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/dp.png").centerCrop().resize(1364, 298).onlyScaleDown().
                placeholder( R.drawable.progress_animation ).into(dp)

        if(isOnline()) {
            invButton.setOnClickListener {
                val arraytarget = invTarget.split("]")


                val credsArray = credsTarget.replace("[", "").replace("]", "").replace("\"", "").split(",")
                val usernames = ArrayList<String>()
                val passwords = ArrayList<String>()
                var i = 0
                var k = 1
                Log.d("size", "" + credsArray.size)
                val size = credsArray.size
                while (i < size) {
                    val un = credsArray[i]
                    usernames.add(un)
                    Log.d("usernames", un)
                    i += 2

                }
                while (k < size) {
                    val pw = credsArray[k]
                    passwords.add(pw)
                    Log.d("passwords", pw)
                    k += 2
                }
                val splitindex = splitText(arraytarget[0].replace("[", "").replace("]", ""))

                countries = arraytarget[1].replace("\"", "").replace("[", "").split(",") as ArrayList<String>

                indescriptions = arraytarget[2].replace("\"", "").replace("[", "").replace("\\n", "\n").replace("\\r", "\r").replace("\\", "").split(",") as ArrayList<String>


                val intent = Intent(this, InvestActivity::class.java)
                intent.putExtra("imageurls", splitindex)
                intent.putExtra("countries", countries)
                intent.putExtra("description", indescriptions)
                intent.putExtra("usernames", usernames)
                intent.putExtra("passwords", passwords)
                startActivity(intent)

            }



            timel.setOnClickListener {


                pastTarget = pastTarget.replace("[", "").replace("]", "")
                val splitted = splitText(pastTarget)

                upcTarget = upcTarget.replace("[", "").replace("]", "")
                val splittedupc = splitTextUpc(upcTarget)

                val intent = Intent(this, ActivityEvents::class.java)
                intent.putExtra("datadoc", splitted)
                intent.putExtra("upcoming", splittedupc)

                startActivity(intent)

            }



            galbutton.setOnClickListener{

                val arraytarget = galleryTarget.split("]")

                galleryMediaUrl=arraytarget[0].
                        replace("[","").
                        replace("\\","").
                        replace("\"","").
                        replace("-225x300","").
                        replace(" ","").split("}")as ArrayList<String>
                var i=0
                while(i<galleryMediaUrl.size) {
                    Log.d("HomeGal", galleryMediaUrl[i])
                    i++
                }
                galleryThumb=arraytarget[1].
                        replace("[","").
                        replace("\\","").
                        replace("\"","").
                        replace("-225x300","").split(",")as ArrayList<String>

                galleryTitle=arraytarget[2].
                        replace("[","").
                        replace("\\","").
                        replace("\"","").split(",")as ArrayList<String>

                val intent: Intent = Intent(this, GalleryActivity::class.java)
                intent.putExtra("galleryMediaUrl",galleryMediaUrl)
                intent.putExtra("galleryThumb",galleryThumb)
                intent.putExtra("galleryTitle",galleryTitle)

                startActivity(intent)
            }
            bp.setOnClickListener() {
                val intent: Intent = Intent(this, PartnerActivity::class.java)
                startActivity(intent)
            }
            art.setOnClickListener() {
                val intent: Intent = Intent(this, ArticleReaderActivity::class.java)
                startActivity(intent)
            }
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