package diplomatssummit.com.diplomatssummit

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import diplomatssummit.com.diplomatssummit.events.DS_Events
import kotlinx.android.synthetic.main.timeline_events.*
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.ryan.rv_gallery.GalleryRecyclerView
import diplomatssummit.com.diplomatssummit.app_ui.CustomRecyclerAdapter
import diplomatssummit.com.diplomatssummit.databases.PeMethods


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DS_Events.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DS_Events.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class gallery : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

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

    /*fun connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver")

            // connect way #1
            val url1 = "jdbc:mysql://103.252.236.42/timeline_events.db"
            val user = "diplomatsadmin"
            val password = "5reaK!@#"

            val conn1 = DriverManager.getConnection(url1, user, password)
            if (conn1 != null) {
                println("Connected to the database test1")
            }

            // connect way #2
            val url2 = "jdbc:mysql://103.252.236.42/timeline_events.db?user=diplomatsadmin&password=5reaK!@#"
            val conn2 = DriverManager.getConnection(url2)
            if (conn2 != null) {
                println("Connected to the database test2")
            }

            // connect way #3
            val url3 = "jdbc:mysql://103.252.236.42/timeline_events.db"
            val info = Properties()
            info["user"] = "diplomatsadmin"
            info["password"] = "5reaK!@#"

            val conn3 = DriverManager.getConnection(url3, info)
            if (conn3 != null) {
                println("Connected to the database test3")
            }
        } catch (ex: SQLException) {
            println("An error occurred. Maybe user/password is invalid")
            ex.printStackTrace()
        } catch (e: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }*/


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.gallery_rv, container, false)

        val mRecyclerView:RecyclerView = view.findViewById(R.id.rv_list)
        mRecyclerView.adapter= CustomRecyclerAdapter(context,getDatas())

        mRecyclerView.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false))
        mRecyclerView.setAdapter(adapter)
        /*val mWebView: WebView = view.findViewById(R.id.webviewgal)
        val url="https://diplomatssummit.com/mobile/gallery.php"
        mWebView.loadUrl(url)
        connect()
        // Enable Javascript
        val webSettings = mWebView.getSettings()
        webSettings.setDomStorageEnabled(true)
        webSettings.setJavaScriptEnabled(true)*/




        // Force links and redirects to open in the WebView instead of in a browser
        /*mWebView.webViewClient=object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view?.visibility= View.INVISIBLE
                progressBar.visibility= View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.visibility= View.VISIBLE
                progressBar.visibility= View.INVISIBLE
            }
        }*/



        // TODO: Rename method, update argument and hook method into UI event
        fun onButtonPressed(uri: Uri) {
            listener?.onFragmentInteraction(uri)
        }
        return view



    }

    fun getDatas(): MutableList<String>? {
        val sampleObj = PeMethods()
        val ar= sampleObj.readMediaRowsBasedOnType(1)
        val size=sampleObj.itemsize()
        var imagelist:MutableList<String>
        imagelist= arrayListOf()
        var i=0

        while (i<size) {
            var imagePath = ar[i].MediaUrl
            imagePath?.let { imagelist.add(i, it) }
            Log.d("test2",imagePath)

            i++
        }


        return imagelist
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
                gallery().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


}