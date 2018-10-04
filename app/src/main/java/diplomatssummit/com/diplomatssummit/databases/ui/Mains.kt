package diplomatssummit.com.diplomatssummit.databases.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import diplomatssummit.com.diplomatssummit.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_display.*


class Mains : BaseActivity() {
    lateinit var listAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_display)

        with (list) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@Mains)
            listAdapter = MainAdapter()
            adapter = listAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        listAdapter.add()
        list.smoothScrollToPosition(0)
        return true
    }

}
