package diplomatssummit.com.diplomatssummit.databases

import android.app.Fragment
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.SamplePagerAdapter

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

class PastEvents : Fragment() {

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    val tv:TextView?=null
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

    /*Populate images from DB onto the view*/
    private fun initializeWidgets(view: View) {

        var imageList:ArrayList<String>?=null
        imageList = populateDBimages()
        val switcher: Button =view.findViewById(R.id.switcher)
        var pe="PAST EVENTS"
        var title="UPCOMING EVENTS"
        val tv:TextView=view.findViewById(R.id.tv)
        val tv2:TextView=view.findViewById(R.id.tv2)
        page(view, imageList!!)
        tv.text=pe
        tv2.text=title

        switcher.setOnClickListener{
            val upc=DbFlow()
            openFragment(upc)
        }
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    /*Populate adapter with the necessary DB input*/
    fun page(view: View,imageList:ArrayList<String>){
        val viewpager:ViewPager = view.findViewById(R.id.sample_pager)
        val pagerAdapter = SamplePagerAdapter(context, imageList)
        viewpager.adapter = pagerAdapter
        val springdot:SpringDotsIndicator=view.findViewById(R.id.spring_dot)
        springdot.setViewPager(viewpager)
    }


    fun populateDBimages(): ArrayList<String>? {

        val sampleObj = PeMethods()
        val ar= sampleObj.readMediaRowsBasedOnType(1)
        val size=sampleObj.itemsize()
        var imagelist= arrayListOf<String>()
        var i=0

        while (i<size) {
            var imagePath = ar[i].MediaUrl
            imagePath?.let { imagelist.add(i, it) }
            Log.d("test2",imagePath)

            i++
        }


        return imagelist
    }



}