package diplomatssummit.com.diplomatssummit.app_ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.databases.GtableMethods
import diplomatssummit.com.diplomatssummit.invest.OpportunityActivity

import java.util.ArrayList


class InvAuthdapter(private val mContext: Context, private val mDatas: List<String>?, private val mTitles: MutableList<String>?, private val mContent: MutableList<String>?) : RecyclerView.Adapter<InvAuthdapter.MyHolder>() {




    private var mOnItemPhotoChangedListener: OnItemPhotoChangedListener? = null


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        super.onAttachedToRecyclerView(recyclerView)
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val itemView = LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false)
        return MyHolder(itemView)

    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val imageUrl = mDatas!![position]
        val imageTitle = mTitles!![position]
        val contents=mContent!![position]
        val intent=Intent(mContext,OpportunityActivity::class.java)
        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.mView)

        holder.mTitles.text = imageTitle

        /*Animation animation,imageanim;
        animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_up);
        imageanim = AnimationUtils.loadAnimation(mContext,R.anim.image_slideup);
        holder.mTitles.startAnimation(animation);
        holder.mView.startAnimation(imageanim);*/
        val place = ArrayList<String>()
        holder.mChange.setOnClickListener {
            intent.putExtra("Country",imageTitle)
            intent.putExtra("MediaBLOB",imageUrl)
            intent.putExtra("DescBLOB",contents)
            mContext.startActivity(intent)
        }

    }


    fun getTitle(pos: Int): List<*> {
        val imageTitle = mTitles!![pos]
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
        val inDesc: TextView

        var mChange: Button

        init {
            mView = itemView.findViewById(R.id.iv_photo)
            mTitles = itemView.findViewById(R.id.gallery_titles)
            mChange = itemView.findViewById(R.id.fab_change)
            inDesc = itemView.findViewById(R.id.inDesc)

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

    private inner class TAG
}