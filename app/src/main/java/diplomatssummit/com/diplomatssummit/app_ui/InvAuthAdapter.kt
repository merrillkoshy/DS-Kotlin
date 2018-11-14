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

import diplomatssummit.com.diplomatssummit.R
import diplomatssummit.com.diplomatssummit.databases.GtableMethods
import diplomatssummit.com.diplomatssummit.invest.OpportunityActivity
import okhttp3.*
import java.io.IOException

import java.util.ArrayList


class InvAuthdapter(private val mContext: Context, private val mDatas: List<String>?, private val mTitles: MutableList<String>?, private val mContent: MutableList<String>?) : RecyclerView.Adapter<InvAuthdapter.MyHolder>() {

    private var countryDetails = String()


    private var mOnItemPhotoChangedListener: OnItemPhotoChangedListener? = null


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        super.onAttachedToRecyclerView(recyclerView)
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val itemView = LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false)
        return MyHolder(itemView)

    }

    fun fetchJson_CountryDetails() {
        val url = "http://diplomatssummit.com/volley/opportunityactivity.php"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                //Slicing the response
                countryDetails = body.toString()

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val imageUrl = mDatas!![position]
        val imageTitle = mTitles!![position]
        val contents=mContent!![position]
        val intent=Intent(mContext,OpportunityActivity::class.java)
        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.mView)
        fetchJson_CountryDetails()

        holder.mTitles.text = imageTitle

        var description=ArrayList<String>()
        /*Animation animation,imageanim;
        animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_up);
        imageanim = AnimationUtils.loadAnimation(mContext,R.anim.image_slideup);
        holder.mTitles.startAnimation(animation);
        holder.mView.startAnimation(imageanim);*/
        val place = ArrayList<String>()
        holder.mChange.setOnClickListener {
            val arraytarget = countryDetails.split("]")
/*
            val inMedia=arraytarget[2].replace("[","").replace("\\","").replace("\"","").split(",")*/
            val mediaUrl=arraytarget[0].replace("[","").replace("\\r","\r").replace("\\n","\n").replace("\\","").replace("\"","").split(",") as ArrayList<String>
            val countries=arraytarget[1].replace("[","").replace("\"","").split(",")as ArrayList<String>
            val sizeDum=arraytarget[2].replace("[","").replace("\\","").replace("\"","").split("},")
            var b=0
            while(b<sizeDum.size)
            {
                description.add(sizeDum[b])
                b++
            }
            val inMedia=arraytarget[3].replace("[","").replace("\\r","\r").replace("\\n","\n").replace("\\","").replace("\"","").split("$")as ArrayList<String>

            intent.putStringArrayListExtra("mediaUrl",mediaUrl)
            intent.putStringArrayListExtra("countries",countries)
            intent.putStringArrayListExtra("description",description)
            intent.putStringArrayListExtra("inMedia",inMedia)
            intent.putExtra("Country",imageTitle)
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