package diplomatssummit.com.diplomatssummit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.TextView
import diplomatssummit.com.diplomatssummit.Dataclasses.DCResponse
import diplomatssummit.com.diplomatssummit.app_ui.Indicators.IndefinitePagerIndicator
import diplomatssummit.com.diplomatssummit.app_ui.PartnerRCAdapter
import kotlinx.android.synthetic.main.activity_events.*
import okhttp3.*
import java.io.IOException


class BetaActivity:AppCompatActivity() {

    private var targetText = String()
    private var resultArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.betatest)

        fetchJson()
        initialiseWidgets()

    }

    fun fetchJson() {

        val url = "https://diplomatssummit.com/volley/restactivity.php"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                //Slicing the response
                targetText = body.toString()

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun initialiseWidgets() {
        val button: Button = findViewById(R.id.button)
        button.text = "GET"
        val tv: TextView = findViewById(R.id.tv)
        tv.text = "Waiting for request"
        button.setOnClickListener {
            val newTarget = targetText.replace("[", "").replace("]", "")
            targetText = targetText.replace("[", "").replace("]", "")
            val splitted=splitText(targetText)
            tv.text = splitted[0]

            val intent=Intent(this,DCResponse::class.java)
            intent.putExtra("datadoc",splitted)
            startActivity(intent)
            val pastAdapter: PartnerRCAdapter = PartnerRCAdapter(this,splitted)
            val past: RecyclerView =findViewById(R.id.pastRv)
            past.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
            past.adapter=pastAdapter
            val pastin: IndefinitePagerIndicator =findViewById(R.id.pastIndicator)
            pastin.attachToRecyclerView(pastRv)
        }

    }

    fun splitText(targetText: String): ArrayList<String> {
        var targetArray = targetText.split(",")
        val size = targetArray.size
        var i = 0
        while (i < size) {
            val buffer = "https://diplomatssummit.com/" + targetArray[i].replace("\"", "").replace("\\", "").replace(" ", "")
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
}








/*
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class BetaActivity:AppCompatActivity(){

    private val TAG = this.javaClass.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseWidgets()
        RestTask().execute("GET")
    }

    fun initialiseWidgets(){

        setContentView(R.layout.betatest)
        val sendButton:Button=findViewById(R.id.button)
        sendButton.text="GET"

        val text:TextView=findViewById(R.id.tv)
        text.text="test"
    }

    inner class RestTask:AsyncTask<String, Boolean, String>(){
        var actionType = "N. A"

        override fun doInBackground(vararg p0: String): String {

            var response= String()

            try {

                actionType=p0[0]

                val obj:URL= URL("https://diplomatssummit.com/volley/restactivity.php")
                val con=obj.openConnection() as HttpsURLConnection

                con.requestMethod=actionType
                con.setRequestProperty("Content-Type", "application/json")

                val responseCode=con.responseCode
                var ipStream:InputStream

                if (responseCode >= 400)
                    ipStream = con.errorStream
                else
                    ipStream=con.inputStream

                val result = ByteArrayOutputStream()


                val buffer = ByteArray(1024)
                var length=ipStream.read(buffer)

                while (length != -1) {
                    result.write(buffer, 0, length)
                }

                ipStream.close()
                response=result.toString("UTF-8")
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            return response
        }

        override fun onPostExecute(response: String?) {
            Log.d(TAG, "web response - $actionType: $response")
        }
    }


}*/
