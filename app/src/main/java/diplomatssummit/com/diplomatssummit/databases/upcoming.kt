package diplomatssummit.com.diplomatssummit.databases

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
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

class DbFlow : Fragment(),PastEvents.OnFragmentInteractionListener{



    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
        initializeWidgets(view)


    }

    /*Populate images from DB onto the view*/
    private fun initializeWidgets(view: View) {

        var title="UPCOMING EVENTS"
        var pe="PAST EVENTS"
        var imageList:ArrayList<String>?=null
        val switcher: Button =view.findViewById(R.id.switcher)

        imageList = populateDBimages()

        val tv:TextView=view.findViewById(R.id.tv)
        val tv2:TextView=view.findViewById(R.id.tv2)
        tv2.text=pe
        page(view, imageList!!)
        tv.text=title




        switcher.setOnClickListener{
            val past=PastEvents()
            openFragment(past)
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

    fun populateDBimages2(): ArrayList<String>? {

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




