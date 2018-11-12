package diplomatssummit.com.diplomatssummit.Dataclasses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class DCResponse:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data=intent.getStringArrayListExtra("datadoc")
        val size=data.size
        Log.d("arraysiz",""+size)
    }
}