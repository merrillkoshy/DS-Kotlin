package diplomatssummit.com.diplomatssummit

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import android.webkit.WebViewClient
import android.webkit.WebSettings




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BlankFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Eventsds : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_events, container, false)



//        val doc = Jsoup.connect("https://10times.com/dubai-ae").get()
//        val stringer = "#content > tr:nth-child(" + 1 + ")"
//        val selecting = doc.select(stringer)



        val textView: TextView = view.findViewById(R.id.textView)
        val document = Jsoup.connect("https://10times.com/dubai-ae").get()
        val parsed = Jsoup.parseBodyFragment("https://10times.com/dubai-ae");
        val body = document.body()
        val paragraphs = body.getElementsByTag("p")
        for (paragraph in paragraphs) {
            System.out.println(paragraph.text())
        }
        val stringer = "#content > tr:nth-child(" + 1 + ")"
        val selecting = document.select(stringer)
        val mHandler = Handler()
        val mRunnable= Runnable { textView.setText(selecting.toString()) }
        mHandler.post(mRunnable)





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

//    object JsoupTester {
//        @JvmStatic
//        fun main(args: Array<String>) {
//
//            val html = "https://10times.com/dubai-ae"
//            val document = Jsoup.parseBodyFragment(html)
//            val body = document.body()
//            val paragraphs = body.getElementsByTag("p")
//            for (paragraph in paragraphs) {
//                System.out.println(paragraph.text())
//            }
//        }
//    }
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
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                Eventsds().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

//    var background = object : Thread() {
//        override fun run() {
//            val doc: Document
//            try {
//                val doc = Jsoup.connect("https://10times.com/dubai-ae").get()
//                val stringer = "#content > tr:nth-child(" + 1 + ")"
//                val selecting = doc.select(stringer)
//                this@Eventsds.runOnUiThread(java.lang.Runnable {
//                    val textView: TextView = view(R.id.textView2)
//                    textView.setText(selecting.toString())
//                })
//
////                for (i in 1..2) {
////                    val stringer = "#content > tr:nth-child(" + i + ")"
////                    val selecting = doc.select(stringer)
////                    val s = Jsoup.parse(selecting.toString())
////                    val pattern = "<[^>]*>".toRegex()
////
////                    var str = s.toString()
////                    val oldValue = pattern
////                    val newValue = ""
////
////                    val output = "testing"
//////                    val output = str.replace(oldValue, newValue)
////
////
////                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//
//    }
}

