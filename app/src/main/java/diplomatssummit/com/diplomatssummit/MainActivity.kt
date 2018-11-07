package diplomatssummit.com.diplomatssummit


import android.app.Fragment
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import diplomatssummit.com.diplomatssummit.Gallery.gallery
import diplomatssummit.com.diplomatssummit.events.DS_Events
import diplomatssummit.com.diplomatssummit.homepage.Home
import diplomatssummit.com.diplomatssummit.invest.DS_Invest
import diplomatssummit.com.diplomatssummit.events.timeline_events
import diplomatssummit.com.diplomatssummit.articles.articles


class MainActivity : AppCompatActivity(),
        DS_Events.OnFragmentInteractionListener,
        Home.OnFragmentInteractionListener,
        DS_Invest.OnFragmentInteractionListener,
        timeline_events.OnFragmentInteractionListener,
        gallery.OnFragmentInteractionListener,
        partners.OnFragmentInteractionListener,
        articles.OnFragmentInteractionListener{

    private var mTextMessage: TextView? = null

//    private val a = 5
//    private val b = 10
//    private var lv: ListView? = null
//    private var customeAdapter: CustomAdapter? = null
//    private var imageModelArrayList: ArrayList<ImageModel>? = null
//    private val myImageList = intArrayOf(R.drawable.events, R.drawable.inv, R.drawable.art, R.drawable.bp)
//    private val myImageNameList = arrayOf("Events", "Investment Opportunities", "President's Articles/Profile", "Partners")

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                title = "Diplomats Summit"
                val homeFragment = Home.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_events -> {
                title = "Events in Dubai"
                val eventsFragment = DS_Events.newInstance()
                openFragment(eventsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                title = ""
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val fm = supportFragmentManager
//
//        //if you added fragment via layout pagers
//        val fragment = fm.findFragmentById(R.id.webviewEvent)
//        val fview=fragment?.view
//        val mWebView: WebView = fview!!.findViewById(R.id.webviewEvent)
//
//        val webSettings = mWebView.getSettings()
//        webSettings.setDomStorageEnabled(true);
//
//
//        webSettings.setJavaScriptEnabled(true)
//        webSettings.setAppCacheEnabled(true)
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT)



//        lv = findViewById(R.id.listView) as ListView
//
//        imageModelArrayList = populateList()
//        Log.d("hjhjh", imageModelArrayList!!.size.toString() + "")
//        customeAdapter = CustomAdapter(this, imageModelArrayList!!)
//        lv!!.adapter = customeAdapter


        val homeFragment = Home.newInstance()
        openFragment(homeFragment)



        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



    }

//    private fun populateList(): ArrayList<ImageModel> {
//
//        val list = ArrayList<ImageModel>()
//
//        for (i in 0..3) {
//            val imageModel = ImageModel()
//            imageModel.setNames(myImageNameList[i])
//            imageModel.setImage_drawables(myImageList[i])
//            list.add(imageModel)
//        }
//
//        return list
//    }

    override fun onFragmentInteraction(uri: Uri) {
        this@MainActivity.runOnUiThread(java.lang.Runnable {
            title = "Diplomats Summit"
            val homeFragment = Home.newInstance()
            openFragment(homeFragment)
        })
    }



}