package diplomatssummit.com.diplomatssummit.Gallery

import android.app.Fragment
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.GalleryPagerAdapter
import diplomatssummit.com.diplomatssummit.events.DS_Events
import diplomatssummit.com.diplomatssummit.homepage.Home

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

class Gallery_InsidePage : Fragment(),Home.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var imagelist: ArrayList<String>? = null
    private var size=0;


    fun setImagelist(imagelist: ArrayList<String>) {
        this.imagelist = imagelist
        var i=0
        this.size=imagelist.size
        Log.d("size_test",size.toString())
        while(i<size)
        {
            Log.d("gip_test",imagelist[i])
            i++
        }

        val gip=Home.newInstance()
        openFragment(gip)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view=inflater.inflate(R.layout.gallery_inside_vpager, container, false)
        Log.d("check","onCreate")
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoadWidgets(view)
    }

    fun LoadWidgets(view: View) {
        page(view,imagelist!!)

    }

    /*Populate adapter with the necessary DB input*/
    fun page(view: View,imageList:ArrayList<String>){
        val viewpager: ViewPager = view.findViewById(R.id.inside_pager)
        val pagerAdapter = GalleryPagerAdapter(context, imageList)
        viewpager.adapter = pagerAdapter
        val springdot: SpringDotsIndicator =view.findViewById(R.id.spring_dot)
        springdot.setViewPager(viewpager)
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}




