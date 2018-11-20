package diplomatssummit.com.diplomatssummit.app_ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.Gallery.Gal_InsidePageActivity
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.util.DLog
import diplomatssummit.com.diplomatssummit.databases.GtableMethods
import java.util.ArrayList
import java.util.regex.Pattern

class RecyclerAdapter(private val mContext: Context, private val mDatas: List<String>?,private var mTitles: List<String>,private val mMediaUrl: ArrayList<String>): RecyclerView.Adapter<RecyclerAdapter.MyHolder>() {

    private val TAG = this.javaClass.getSimpleName()
    var t1=String()
    var flag = 0

    private var mOnItemPhotoChangedListener: OnItemPhotoChangedListener? = null


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        DLog.d(TAG, "RecyclerAdapter onAttachedToRecyclerView")
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        DLog.d(TAG, "RecyclerAdapter onCreateViewHolder" + " width = " + parent.width)
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false)
        return MyHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        DLog.d(TAG, "RecyclerAdapter onBindViewHolder--> position = $position")
        val imageUrl = mDatas!![position]
        val imageTitle = mTitles[position]
        var i = 0
        var titlename=ArrayList<String>()
        var urlFromTitle=ArrayList<String>()
        while (i < mMediaUrl.size) {
            t1 = mMediaUrl[i]
            titlename.add(t1.replace(",","").split("{")[0])
            /*urlFromTitle.add(t1.split("{")[1])*/
            if(titlename[i].trim().toString()==imageTitle.replace(" ","").trim().toString())
                flag=i
            i++
        }

        Log.d("Title", imageTitle)
        Log.d("Flagged", ""+flag)
        Log.d("mMediaUrl", mMediaUrl[position])
        val mMediaLink=mMediaUrl[flag]
        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.mView)
        val getTitleonBind1 = getTitle(position)
        val getTitleonBind2 = getTitleonBind1.get(0).toString()
        val getTitleonBind3 = getTitleonBind2.replace("-225x300".toRegex(), "").split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val getTitleonBind = getTitleonBind3[2]
        val size = getTitleonBind3.size
        holder.mTitles.setText(imageTitle)
        val place = ArrayList<String>()

        holder.mChange.setOnClickListener {
            place.clear()
            var i = 0
            while (i < size) {
                Log.d("clickedTitle", getTitleonBind3[i])
                place.add(getTitleonBind3[i])
                i++
            }

            val j = Intent(mContext, Gal_InsidePageActivity::class.java)
            j.putStringArrayListExtra("galCon", mMediaUrl)
            j.putExtra("titleGal", imageTitle)
            j.putExtra("mediasGal", mMediaLink)
            mContext.startActivity(j)
        }
    }


    fun getTitle(pos: Int): List<*> {
        val imageTitle = mTitles[pos]
        val ob2 = GtableMethods()
        return ob2.readContentFromTitle(imageTitle)
    }


    override fun getItemCount(): Int {
        var count = 0
        if (mDatas != null) count = mDatas.size
        return count
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView: ImageView
        val mTitles: TextView
        var mChange: Button

        init {
            mView = itemView.findViewById(R.id.iv_photo)
            mTitles = itemView.findViewById<TextView>(R.id.gallery_titles)
            mChange = itemView.findViewById(R.id.fab_change)
        }
    }


//    public int getResId(int position) {
//        return mDatas == null ? 0 : mDatas.get(position);
//    }

    fun setOnItemPhotoChangedListener(mOnItemPhotoChangedListener: OnItemPhotoChangedListener) {
        this.mOnItemPhotoChangedListener = mOnItemPhotoChangedListener
    }

    interface OnItemPhotoChangedListener {
        fun onItemPhotoChanged()
    }

}