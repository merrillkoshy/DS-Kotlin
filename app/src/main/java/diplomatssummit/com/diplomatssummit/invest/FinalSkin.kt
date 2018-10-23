package diplomatssummit.com.diplomatssummit.invest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.R

class FinalSkin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_skin)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val region=intent.getStringExtra("RegionName")
        val regionurl=intent.getStringExtra("RegionUrl")
        val inDesc=intent.getStringExtra("inDescription")
        Log.d("testinDesc",inDesc)

        val iv:ImageView=findViewById(R.id.finalSkinImage)
        val tv:TextView=findViewById(R.id.finalSkinContent)
        Picasso.get().load(regionurl).resize(400,200).centerCrop().into(iv)
        tv.setText(region)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
