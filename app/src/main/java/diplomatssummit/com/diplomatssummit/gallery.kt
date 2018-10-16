package diplomatssummit.com.diplomatssummit

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
import android.util.Log
import diplomatssummit.com.diplomatssummit.app_ui.AnimManager
import diplomatssummit.com.diplomatssummit.app_ui.GalleryRecyclerView
import diplomatssummit.com.diplomatssummit.app_ui.RecyclerAdapter
import diplomatssummit.com.diplomatssummit.databases.GtableMethods
import diplomatssummit.com.diplomatssummit.Gallery.Gallery_InsidePage


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
    private val mMainThreadHandler: Handler = Handler()

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


        val mRecyclerView:GalleryRecyclerView=view.findViewById(R.id.rv_list)
        val recyclerAdapter:RecyclerAdapter=RecyclerAdapter(
                context,
                getDatas(),
                getTitles()
        )




        val itemClickListener:GalleryRecyclerView.OnItemClickListener
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.adapter=recyclerAdapter


        mRecyclerView
                // set scroll speed（pixel/s）
                .initFlingSpeed(9000)
                // set page distance and visible distance of the nearby.
                .initPageParams(0, 40)
                // set the animation factor
                .setAnimFactor(0.1f)
                // set animation type. you can choose AnimManager.ANIM_BOTTOM_TO_TOP or AnimManager.ANIM_TOP_TO_BOTTOM
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)

                // set whether auto play
                .autoPlay(false)
                // set auto play intervel
                .intervalTime(2000)
                // set default position
                .initPosition(1)
                // finally call method
                .setUp();


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
            ip2?.let { imagelist.add(i, it) }
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