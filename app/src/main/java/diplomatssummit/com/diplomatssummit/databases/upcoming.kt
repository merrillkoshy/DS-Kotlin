package diplomatssummit.com.diplomatssummit.databases

import diplomatssummit.com.diplomatssummit.app_ui.SamplePagerActivity

import android.R.attr.checked
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.*
import diplomatssummit.com.diplomatssummit.CustomRecyclerAdapter
import diplomatssummit.com.diplomatssummit.app_ui.SamplePagerAdapter

import diplomatssummit.com.diplomatssummit.articles.articles

import diplomatssummit.com.diplomatssummit.events.timeline_events
import diplomatssummit.com.diplomatssummit.invest.DS_Invest
import kotlinx.android.synthetic.main.main_display.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Home.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 *
 */

class DbFlow : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_sample_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initializeWidgets(view);
    }

    private fun initializeWidgets(view: View) {

        val viewpager:ViewPager = view.findViewById(R.id.sample_pager)
        //adapter creation

        val imageUrl = "https://raw.githubusercontent.com/JakeWharton/butterknife/master/website/static/logo.png"
        val imageList = populateDBimages()

        val pagerAdapter = SamplePagerAdapter(context, imageList)
        viewpager.adapter = pagerAdapter
    }


fun populateDBimages(): ArrayList<String>? {

        val sampleObj = SampleMethods()
        val ar= sampleObj.readMediaRowsBasedOnType(1)
        val size=sampleObj.itemsize()
        var imagelist= arrayListOf<String>()
        var i=0

        while (i<size) {
            var imagePath = ar[i].MediaUrl
            imagelist.add(i,imagePath)
            Log.d("test",imagePath)

            i++
        }


    return imagelist
    }
}




