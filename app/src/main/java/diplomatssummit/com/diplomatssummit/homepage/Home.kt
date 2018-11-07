package diplomatssummit.com.diplomatssummit.homepage

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.Gallery.gallery
import diplomatssummit.com.diplomatssummit.PartnerActivity
import diplomatssummit.com.diplomatssummit.articles.ArticleReaderActivity
import diplomatssummit.com.diplomatssummit.events.ActivityEvents
import diplomatssummit.com.diplomatssummit.invest.DS_Invest
import diplomatssummit.com.diplomatssummit.invest.InvestActivity
import diplomatssummit.com.diplomatssummit.partners


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
class Home : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    val eventButton: ImageView? = null
    val invButton: ImageView? = null
    val timel:ImageView?=null
    val galbutton:ImageView?=null
    val bp:ImageView?=null
    val art:ImageView?=null



    fun getScreenWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().getDisplayMetrics().heightPixels
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
//        val eventButton: ImageView =view.findViewById(R.id.tradeevents)

        val invButton: ImageView=view.findViewById(R.id.inv)
        val timel: ImageView=view.findViewById(R.id.tradeevents)
        val galbutton: ImageView=view.findViewById(R.id.gal)
        val bp: ImageView=view.findViewById(R.id.bp)
        val dp: ImageView=view.findViewById(R.id.dp)
        val art: ImageView=view.findViewById(R.id.art)


        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/events.png").centerCrop().resize(1348, 555).onlyScaleDown().into(timel)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/inv.png").centerCrop().resize(1000, 700).onlyScaleDown().into(invButton)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/gallery.png").centerCrop().resize(600, 400).onlyScaleDown().into(galbutton)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/bp.jpg").centerCrop().resize(1224, 717).onlyScaleDown().into(bp)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/art.jpg").centerCrop().resize(600, 400).onlyScaleDown().into(art)
        Picasso.get().load("https://diplomatssummit.com/mobile/hp_assets/dp.png").centerCrop().resize(1364, 298).onlyScaleDown().into(dp)


//        eventButton.setOnClickListener() {
//            val eveFragment = DS_Events.newInstance()
//            openFragment(eveFragment)
//        }
        invButton.setOnClickListener(){
           val intent:Intent= Intent(context, InvestActivity::class.java)
            startActivity(intent)
        }
        timel.setOnClickListener(){
            /*val tlFragment=timeline_events.newInstance()
            openFragment(tlFragment)*/
            val intent:Intent= Intent(context, ActivityEvents::class.java)
            startActivity(intent)
        }
        galbutton.setOnClickListener(){
            val galFragment= gallery.newInstance()
            openFragment(galFragment)
        }
        bp.setOnClickListener(){
            val intent:Intent=Intent(context,PartnerActivity::class.java)
            startActivity(intent)
        }
        art.setOnClickListener(){
            val intent:Intent= Intent(context, ArticleReaderActivity::class.java)
            startActivity(intent)
        }
//        val mWebView: WebView = view.findViewById(R.id.webview)
//        mWebView.loadUrl("http://diplomatssummit.com/mobile/index.php")
//
//        // Enable Javascript
//        val webSettings = mWebView.getSettings()
//        webSettings.setJavaScriptEnabled(true)
//
//        // Force links and redirects to open in the WebView instead of in a browser
//        mWebView.setWebViewClient(WebViewClient())
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
        try {
            val childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.isAccessible = true
            childFragmentManager.set(this, null)

        } catch (e: NoSuchFieldException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }

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
                Home().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


}
