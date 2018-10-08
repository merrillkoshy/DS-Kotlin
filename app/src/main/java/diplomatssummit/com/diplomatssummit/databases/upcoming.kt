package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.structure.BaseModel
import com.raizlabs.android.dbflow.annotation.Table
import android.R.attr.checked
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.*
import diplomatssummit.com.diplomatssummit.CustomRecyclerAdapter

import diplomatssummit.com.diplomatssummit.articles.articles
import diplomatssummit.com.diplomatssummit.databases.core.MediaTable
import diplomatssummit.com.diplomatssummit.events.timeline_events
import diplomatssummit.com.diplomatssummit.invest.DS_Invest
import kotlinx.android.synthetic.main.main_display.*
import java.io.File

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
class dbflow : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    var imageModel:ArrayList<ImageModel> = ArrayList()
    val main_recyclerview:RecyclerView?=null

    val art: ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.main_display, container, false)
        val viewholder = inflater.inflate(R.layout.main_item,container,false)

        val sampleObj = SampleMethods()
        val ar= sampleObj.readMediaRowsBasedOnType(1)
        val size=sampleObj.itemsize()
        var i=0
        while (i<size) {
            var imagePath = ar[i].MediaUrl
            imageModel.add(ImageModel(imagePath))
            Log.d("test",imagePath)
            i++
        }

        val customRecyclerAdapter:CustomRecyclerAdapter= CustomRecyclerAdapter(imageModel)
        val recycler:RecyclerView= view.findViewById(R.id.main_recyclerview)
        recycler.layoutManager=GridLayoutManager(view.context,3)
        recycler.adapter=customRecyclerAdapter
        val iv:ImageView=viewholder.findViewById(R.id.img_view)
        while (i<size) {
            var imagePath = ar[i].MediaUrl
            Picasso.get().load(imagePath).into(iv)
        }
//        val art: ImageView =view.findViewById(R.id.dbflowtest)
//        val medurl=MediaTable().MediaUrl[1].toString()


//        most invaluable code
//        val sampleObj = SampleMethods()
//
//        val ar= sampleObj.readMediaRowsBasedOnType(1)
//        val size=sampleObj.itemsize()
//        Log.d("Picasso_load"," "+ar[1].MediaUrl)
//        Log.d("itSiz"," "+size)
//        Picasso.get().load(ar[0].MediaUrl).into(art)


        return view

    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                dbflow().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


}




