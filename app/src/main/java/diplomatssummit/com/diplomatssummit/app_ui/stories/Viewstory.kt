package diplomatssummit.com.diplomatssummit.app_ui.stories

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import diplomatssummit.com.diplomatssummit.databases.AchievementsMethod



import com.squareup.picasso.Picasso

import java.util.ArrayList
import java.util.Random

import diplomatssummit.com.diplomatssummit.PartnerActivity
import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.app_ui.util.DLog

import android.content.ContentValues.TAG
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class Viewstory(private val mContext: Context, private val mDatas: List<String>,private val mDescription:List<String>,private val mDescriptionLong:List<String>) : RecyclerView.Adapter<Viewstory.MyHolderP>() {

    private var mOnItemPhotoChangedListener: OnItemPhotoChangedListener? = null


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderP {

        val itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_stories, parent, false)
        return MyHolderP(itemView)
    }


    override fun onBindViewHolder(holder: MyHolderP, position: Int) {

        val imageUrl = mDatas[position]
        var descriptionDesiplay=mDescription[position]
        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.mView)
        holder.mChange.text="Read More"
        holder.mDescrip.text=descriptionDesiplay
            holder.mChange.setOnClickListener{
                val intent:Intent= Intent(mContext,DetailedStory::class.java)
                intent.putExtra("imageurl",imageUrl)
                intent.putExtra("descriptionLong",mDescriptionLong[position])
                mContext.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return mDatas.size
    }


    class MyHolderP(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView: ImageView
        var mChange: Button
        var mDescrip:TextView

        init {
            mView = itemView.findViewById(R.id.iv_photo)
            mChange = itemView.findViewById(R.id.read_more)
            mDescrip=itemView.findViewById(R.id.story)
        }
    }


    /*public int getResId(int position) {
        return mDatas == null ? 0 : mDatas.get(position);
    }*/

    fun setOnItemPhotoChangedListener(mOnItemPhotoChangedListener: OnItemPhotoChangedListener) {
        this.mOnItemPhotoChangedListener = mOnItemPhotoChangedListener
    }

    interface OnItemPhotoChangedListener {
        fun onItemPhotoChanged()
    }
}
