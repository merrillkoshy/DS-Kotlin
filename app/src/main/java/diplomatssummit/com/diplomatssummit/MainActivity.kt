package diplomatssummit.com.diplomatssummit

import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.support.v4.app.Fragment

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.io.IOException


class MainActivity : AppCompatActivity(),Home.OnFragmentInteractionListener,Eventsds.OnFragmentInteractionListener {

    private var mTextMessage: TextView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                title = "HomeTest"
                val homeFragment = Home.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_events -> {
                title = "EventTest"
                val eventsFragment = Eventsds.newInstance()
                openFragment(eventsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mTextMessage!!.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)





//        val doc = Jsoup.connect("https://10times.com/dubai-ae").get()
//        val stringer = "#content > tr:nth-child(" + 1 + ")"
//        val selecting = doc.select(stringer)
//        this@MainActivity.runOnUiThread(java.lang.Runnable {
//            val textView: TextView = findViewById(R.id.textView2)
//            textView.setText(selecting.toString())
//        })


    }

    override fun onFragmentInteraction(uri: Uri) {
        this@MainActivity.runOnUiThread(java.lang.Runnable {
            title = "HomeTest"
            val homeFragment = Home.newInstance()
            openFragment(homeFragment)
        })
    }



}