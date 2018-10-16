package diplomatssummit.com.diplomatssummit.Gallery

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
import kotlinx.android.synthetic.main.activity_main.*

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

class Gallery_InsidePage : Fragment(){


    fun populateDBimages(getTitleonBind3: Array<String>): ArrayList<String> {

        val value=getTitleonBind3
        var imagelist= arrayListOf<String>()
        var i=0
        val size=value.size
        while(i<size)
        {
            var imagePath = value[i]
            imagelist.add(i,imagePath)
            Log.d("gip_test",imagePath)
            i++
        }
        return imagelist

    }






}




