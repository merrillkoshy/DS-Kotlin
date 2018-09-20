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
import android.webkit.WebView
import android.webkit.WebViewClient

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.io.IOException


class MainActivity : AppCompatActivity(),Home.OnFragmentInteractionListener,DS_Events.OnFragmentInteractionListener {

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
                title = "Events in Dubai"
                val eventsFragment = DS_Events.newInstance()
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


        val mWebView: WebView =findViewById(R.id.webview)
        mWebView.loadUrl("https://diplomatssummit.com/mobile/index.php")

        // Enable Javascript
        val webSettings = mWebView.getSettings()
        webSettings.setJavaScriptEnabled(true)

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(WebViewClient())



        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



    }

    override fun onFragmentInteraction(uri: Uri) {
        this@MainActivity.runOnUiThread(java.lang.Runnable {
            title = "HomeTest"
            val homeFragment = Home.newInstance()
            openFragment(homeFragment)
        })
    }



}