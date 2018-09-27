package diplomatssummit.com.diplomatssummit.invest

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

import android.webkit.WebResourceRequest
import diplomatssummit.com.diplomatssummit.R
import kotlinx.android.synthetic.main.fragment_ds_invest.*


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
class DS_Invest : Fragment() {
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ds_invest, container, false)
        val mWebView: WebView = view.findViewById(R.id.webviewinv)
        val url="https://diplomatssummit.com/business_opportunities.php"
        mWebView.loadUrl(url)

        // Enable Javascript
        val webSettings = mWebView.getSettings()
        webSettings.setDomStorageEnabled(true);


        webSettings.setJavaScriptEnabled(true)

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.webViewClient=object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view?.visibility=View.INVISIBLE
                progressBar.visibility=View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.visibility=View.VISIBLE
                progressBar.visibility=View.INVISIBLE
            }
        }



        // TODO: Rename method, update argument and hook method into UI event
        fun onButtonPressed(uri: Uri) {
            listener?.onFragmentInteraction(uri)
        }
        return view



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
                DS_Invest().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


}