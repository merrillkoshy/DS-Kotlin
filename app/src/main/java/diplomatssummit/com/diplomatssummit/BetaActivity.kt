package diplomatssummit.com.diplomatssummit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.io.BufferedInputStream
import java.net.URL

import javax.net.ssl.HttpsURLConnection

class BetaActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseWidgets()
    }
    private fun initialiseWidgets(){
        setContentView(R.layout.layout_forbetatest)

        val url: URL =URL("https://diplomatssummit.com/mobile/events.php")
        val urlConnection = url.openConnection() as HttpsURLConnection
        try {
            val iostream = BufferedInputStream(urlConnection.inputStream)
            iostream.read()
        } finally {
            urlConnection.disconnect()
        }
    }
}