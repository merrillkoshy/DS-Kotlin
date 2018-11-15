package diplomatssummit.com.diplomatssummit.app_ui

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.R


class GalleryPagerAdapter(private val mContext: Context, private val imageList: List<String>?) : PagerAdapter() {

    private val TAG = this.javaClass.simpleName
    private val slideno: Int = 0

    override fun getCount(): Int {

        var count = 0

        if (imageList != null)
            count = imageList.size

        return count
    }

    override fun instantiateItem(viewGroup: ViewGroup, position: Int): Any {

        val rootView = LayoutInflater.from(mContext).inflate(R.layout.gallery_inside_page, null, false)

        val imageUrl = imageList!![position]

        if(imageUrl=="")
        {
            val strin: String = "https://diplomatssummit.com/mobile/hp_assets/placeholder.jpg"
            val pagerImageView = rootView.findViewById<ImageView>(R.id.galleryinside)
            Log.d("gPA_test", strin)
            Picasso.get().load(strin).centerCrop().fit().into(pagerImageView)
        }else {
            val pagerImageView = rootView.findViewById<ImageView>(R.id.galleryinside)
            Log.d("gPA_test", imageUrl)
            Picasso.get().load(imageUrl).centerCrop().fit().into(pagerImageView)
        }
        viewGroup.addView(rootView)


        return rootView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
