package diplomatssummit.com.diplomatssummit.Dataclasses

import android.os.Bundle
/*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import okhttp3.*
import java.io.IOException

class DCResponse(private val mUrl: String){

    val client= OkHttpClient()
    val request= Request.Builder().url(mUrl).build()




        client.newCall(request).enqueue(object:Callback{
            override fun onResponse(call: Call, response: Response) {
                val body= response.body()?.string()
                var targetText=body.toString()
                val newTarget=targetText.replace("[","").replace("]","")
                splitvalues(newTarget)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })


    fun splitvalues(newTarget: String) {
        var targetArray:List<String>
        targetArray= arrayListOf()
        targetArray=newTarget.split(",")
        val size=targetArray.size
        var i=0
        while(i<size)
        {
            Log.d("arraytarget",targetArray[i].replace("\"","").replace("\\",""))
            i++

        }
    }
}
*/
