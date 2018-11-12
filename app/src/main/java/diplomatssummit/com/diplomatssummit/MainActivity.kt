package diplomatssummit.com.diplomatssummit



import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

import diplomatssummit.com.diplomatssummit.homepage.HomeActivity


class MainActivity : AppCompatActivity(){

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                title = "Diplomats Summit"
                val intent:Intent= Intent(this,HomeActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_events -> {
                title = "Events in Dubai"
                /*val eventsFragment = DS_Events.newInstance()
                openFragment(eventsFragment)*/
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                title = ""
                val intent:Intent= Intent(this,BetaActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



    }





}