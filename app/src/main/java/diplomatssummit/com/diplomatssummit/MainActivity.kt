package diplomatssummit.com.diplomatssummit

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Menu
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.TextView
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

import org.jsoup.Jsoup
import org.jsoup.helper.Validate
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import java.io.IOException
import android.os.AsyncTask



class MainActivity : AppCompatActivity() {

    private var mTextMessage: TextView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mTextMessage!!.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mTextMessage!!.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mTextMessage!!.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

      override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
//          val r =MyTask().execute()
////        val url = "https://10times.com/dubai-ae"
////        val doc = Jsoup.connect(url).get()
////        val title = doc.title()
//        val textView: TextView = findViewById(R.id.textView2)
//        textView.setText("" + r)


        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.loadUrl("https://google.com")
        myWebView.settings.javaScriptEnabled = true
        myWebView.webViewClient = WebViewClient()
        (WebViewClient())

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val myWebView: WebView = findViewById(R.id.webview)
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }



    var background = object : Thread(){
        override fun run() {
            val doc: Document
            try {
                doc = Jsoup.connect("https://10times.com/dubai-ae").get()
                val divTag = doc.getElementById("content")

                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    val textView: TextView = findViewById(R.id.textView2)
                    textView.setText("" + divTag)
                })

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }.start()

}