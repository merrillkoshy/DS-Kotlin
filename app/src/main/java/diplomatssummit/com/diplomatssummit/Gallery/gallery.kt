/*
package diplomatssummit.com.diplomatssummit.Gallery

import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import diplomatssummit.com.diplomatssummit.events.DS_Events
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.SeekBar
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.AnimManager
import diplomatssummit.com.diplomatssummit.app_ui.GalleryRecyclerView
import diplomatssummit.com.diplomatssummit.app_ui.Indicators.IndefinitePagerIndicator
import diplomatssummit.com.diplomatssummit.app_ui.RecyclerAdapter
import diplomatssummit.com.diplomatssummit.databases.GtableMethods



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

*/
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DS_Events.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DS_Events.newInstance] factory method to
 * create an instance of this fragment.
 *
 *//*

class gallery : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private val mMainThreadHandler: Handler = Handler()
    private var mSeekbar: SeekBar? = null
    private val mRecyclerView: GalleryRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        initializeWidgets()
    }



    private fun initializeWidgets() {

        try {
            val progressBar: ProgressBar = view!!.findViewById(R.id.progressBar)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.gallery_rv, container, false)


        val mRecyclerView:GalleryRecyclerView=view.findViewById(R.id.rv_list)
        val recyclerAdapter:RecyclerAdapter=RecyclerAdapter(
                context,
                getDatas(),
                getTitles(),getTitles()
        )




        val itemClickListener:GalleryRecyclerView.OnItemClickListener
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.adapter=recyclerAdapter
        val indicator2: IndefinitePagerIndicator =view.findViewById(R.id.rv_Indicator)
        indicator2.attachToRecyclerView(mRecyclerView)
        mRecyclerView
                // set scroll speed（pixel/s）
                .initFlingSpeed(9000)
                // set page distance and visible distance of the nearby.
                .initPageParams(0, 40)
                // set the animation factor
                .setAnimFactor(0.1f)
                // set animation type. you can choose AnimManager.ANIM_BOTTOM_TO_TOP or AnimManager.ANIM_TOP_TO_BOTTOM
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)

                // set default position
                .initPosition(0)
                // finally call method
                .setUp();


        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                mSeekbar?.setProgress(mRecyclerView.getScrolledPosition());
            }
        })

        mSeekbar?.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mRecyclerView.smoothScrollToPosition(progress);
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })



        // TODO: Rename method, update argument and hook method into UI event
        fun onButtonPressed(uri: Uri) {
            listener?.onFragmentInteraction(uri)
        }
        return view



    }

    fun getDatas(): MutableList<String>? {

        val ob2=GtableMethods()
        val ar2=ob2.readMediaRowsBasedOnType(1)
        val s2=ob2.itemsize()
        var imagelist:MutableList<String>
        var titlelist:MutableList<String>
        imagelist= arrayListOf()
        titlelist= arrayListOf()
        var i=0

        while (i<s2) {
            var ip2=ar2[i].GalleryThumb
            var title=ar2[i].Title
            title?.let { titlelist.add(i,it) }
            ip2!!.replace("-225x300","").let { imagelist.add(i, it) }
            Log.d("testGal",ip2)

            i++
        }


        return imagelist
    }



    fun getTitles(): MutableList<String>? {

        val ob2=GtableMethods()
        val ar2=ob2.readMediaRowsBasedOnType(1)
        val s2=ob2.itemsize()
        var titlelist:MutableList<String>
        titlelist= arrayListOf()
        var i=0

        while (i<s2) {
            var ip2=ar2[i].GalleryThumb
            var title=ar2[i].Title
            title?.let { titlelist.add(i,it) }
            Log.d("testGal",ip2)
            i++
        }
        return titlelist
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun getmMainThreadHandler(): Handler {
        return mMainThreadHandler
    }

    */
/**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     *//*

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        */
/**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         *//*

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                gallery().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


}*/
