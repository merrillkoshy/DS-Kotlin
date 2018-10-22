package diplomatssummit.com.diplomatssummit.invest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import diplomatssummit.com.diplomatssummit.R

class OpportunityActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opportunity)
        val country=intent.getStringExtra("Country")
        val mediaBLOB=intent.getStringExtra("MediaBLOB")
        val descBLOB=intent.getStringExtra("DescBLOB")

        Log.d("oA1",country)
        Log.d("oA2",mediaBLOB)
        Log.d("oA3",descBLOB)
        val heading:TextView=findViewById(R.id.heading)
        heading.setText(country)

    }
}
