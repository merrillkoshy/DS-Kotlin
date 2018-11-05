package diplomatssummit.com.diplomatssummit.app_ui.stories

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.GalleryPagerAdapter
import kotlinx.android.synthetic.main.layout_detailedstory.*

class DetailedStory : AppCompatActivity() {

    var imageUrl= String()
    var descriptionLong=String()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_detailedstory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = ""
        imageUrl=intent.getStringExtra("imageurl")
        descriptionLong=intent.getStringExtra("descriptionLong")
        Picasso.get().load(imageUrl).fit().into(imagestory)
        tvgip.text=descriptionLong

    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }






}