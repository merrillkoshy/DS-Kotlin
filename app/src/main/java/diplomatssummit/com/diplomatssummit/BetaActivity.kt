package diplomatssummit.com.diplomatssummit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import okhttp3.*
import java.io.IOException


class BetaActivity:AppCompatActivity(){

    private var targetText=String()
    private var resultArray=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.betatest)

        fetchJson()
        initialiseWidgets()

    }

    fun fetchJson(){

        val url="https://diplomatssummit.com/volley/restactivity.php"

        val client=OkHttpClient()
        val request=Request.Builder().url(url).build()

        client.newCall(request).enqueue(object:Callback{
            override fun onResponse(call: Call, response: Response) {
                val body= response.body()?.string()

                //Slicing the response
                targetText=body.toString()

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun initialiseWidgets(){
        val button:Button=findViewById(R.id.button)
        button.text="GET"
        val tv:TextView=findViewById(R.id.tv)
        tv.text="Waiting for request"
        button.setOnClickListener{
            val newTarget=targetText.replace("[","").replace("]","")
            splitvalues(newTarget)
            tv.text=newTarget
        }

    }
    fun splitvalues(newTarget: String){
        var targetArray:List<String>
            targetArray= arrayListOf()
            resultArray= arrayListOf()
            targetArray=newTarget.split(",")
            val size=targetArray.size
        var i=0
        while(i<size)
        {
            val buffer=targetArray[i].replace("\"","").replace("\\","")
            resultArray.add(buffer)
            Log.d("arraytarget",targetArray[i].replace("\"","").replace("\\",""))
            i++

        }

    }
        fun getArray(): ArrayList<String> {
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
