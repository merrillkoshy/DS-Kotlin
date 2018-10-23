package diplomatssummit.com.diplomatssummit.invest

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import diplomatssummit.com.diplomatssummit.R

import kotlin.collections.ArrayList


class OpportunityAdapter(private val mContext: Context, private val mRegion: List<String>?, private val mRegionImage: MutableList<String>?, private val inDescription: ArrayList<String>?) : RecyclerView.Adapter<OpportunityAdapter.MyHolder>() {




    private var mOnItemPhotoChangedListener: OnItemPhotoChangedListener? = null


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val itemView = LayoutInflater.from(mContext).inflate(R.layout.recycle_opportunity_layout, parent, false)
        return MyHolder(itemView)

    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {


        val imageTitle = mRegion!![position]
        val imageUrl = mRegionImage!![position]
        Log.d("imageUrlinAdapter",imageUrl)
        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.mView)
        holder.mTitles.text = imageTitle
        holder.mCardView.setOnClickListener{
            val intent=Intent(mContext,FinalSkin::class.java)
            intent.putExtra("RegionName",mRegion[position])
            intent.putExtra("RegionUrl",mRegionImage[position])
            intent.putExtra("InDescription",inDescription)
            mContext.startActivity(intent)
        }

    }





    override fun getItemCount(): Int {
        var count = 0
        if (mRegion != null) count = mRegion.size
        return count
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView: ImageView
        val mTitles: TextView
        val mCardView:CardView

        init {
            mView = itemView.findViewById(R.id.RegionImage)
            mTitles = itemView.findViewById(R.id.RegionName)
            mCardView=itemView.findViewById(R.id.RegionCard)
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