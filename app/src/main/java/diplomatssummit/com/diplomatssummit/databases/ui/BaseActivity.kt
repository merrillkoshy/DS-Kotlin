package diplomatssummit.com.diplomatssummit.databases.ui

import android.app.ActivityManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import diplomatssummit.com.diplomatssummit.R
import kotlinx.android.synthetic.main.main_display.*


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareTaskDescription()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setSupportActionBar(toolbar)
    }

    private fun prepareTaskDescription() {
        if (Build.VERSION.SDK_INT >= 21) {
            if (sTaskDescription == null) {
                val label = getString(R.string.app_name)
                val icon = BitmapFactory.decodeResource(resources, R.drawable.ic_home_black_24dp)
                val colorPrimary = resources.getColor(R.color.primary_dark_material_dark)
                sTaskDescription = ActivityManager.TaskDescription(label, icon, colorPrimary)
            }
            setTaskDescription(sTaskDescription)
        }
    }

    companion object {
        private var sTaskDescription: ActivityManager.TaskDescription? = null
    }
}