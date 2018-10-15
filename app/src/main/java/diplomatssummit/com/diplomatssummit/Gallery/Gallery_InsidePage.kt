package diplomatssummit.com.diplomatssummit.Gallery

import android.app.Fragment
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.R
import kotlinx.android.synthetic.main.activity_splash.*


class Gallery_InsidePage : Fragment() {

    private val mContent=getContent()
    fun getContent(content: List<String>) {
        this.mContent=content
    }
     fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.gallery_inside_page, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initializeWidgets(view)


    }

    fun splitter(split:String){
        var str=split
        var delimiter = ","
        val parts=str.split(delimiter)
        Log.d("split_test",parts[0])
    }

    /*Populate images from DB onto the view*/
    private fun initializeWidgets(view: View) {

        fun getContent(content: List<String>) {
            splitter(content[0])
            Log.d("content_blob", content[0])

            val imageUrl = splitter(content[0]).toString()
            val iv: ImageView = view.findViewById(R.id.galleryinside)
            Picasso.get().load(imageUrl).centerCrop().fit().into(iv)
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    /*Populate adapter with the necessary DB input*//*
    fun page(view: View,imageList:ArrayList<String>){
        val viewpager:ViewPager = view.findViewById(R.id.sample_pager)
        val pagerAdapter = SamplePagerAdapter(context, imageList)
        viewpager.adapter = pagerAdapter
        val springdot:SpringDotsIndicator=view.findViewById(R.id.spring_dot)
        springdot.setViewPager(viewpager)
    }*/


}