package diplomatssummit.com.diplomatssummit.articles

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.R

class ReadSelectedArticle:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initializeWidgets()
    }
    fun initializeWidgets(){
        setContentView(R.layout.activity_readselectedarticle)
        val image:ImageView=findViewById(R.id.readarticleimage)
        val content:TextView=findViewById(R.id.readarticlecontent)
        val retrievedtitle=intent.getStringExtra("heading")
        title = retrievedtitle

        val imageurl=intent.getStringExtra("imageurl")
        Picasso.get().load(imageurl).fit().into(image)

        val story=intent.getStringExtra("story")
        content.text=story
        Log.d("story",story)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}