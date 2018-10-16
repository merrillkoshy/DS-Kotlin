package diplomatssummit.com.diplomatssummit.app_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import diplomatssummit.com.diplomatssummit.Gallery.Gallery_InsidePage;
import diplomatssummit.com.diplomatssummit.R;
import diplomatssummit.com.diplomatssummit.app_ui.util.DLog;
import diplomatssummit.com.diplomatssummit.databases.GtableMethods;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private final String TAG =this.getClass().getSimpleName(); ;
    private Context mContext;
    private List<String> mDatas;
    private List<String> mTitles;
    private List<String> mContent;

    private OnItemPhotoChangedListener mOnItemPhotoChangedListener;


    public RecyclerAdapter(Context mContext, List<String> mDatas,List<String> mTitles) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mTitles=mTitles;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        DLog.d(TAG, "RecyclerAdapter onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DLog.d(TAG, "RecyclerAdapter onCreateViewHolder" + " width = " + parent.getWidth());
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false);
        return new MyHolder(itemView);    }



    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        DLog.d(TAG, "RecyclerAdapter onBindViewHolder" + "--> position = " + position);
        String imageUrl = mDatas.get(position);
        final String imageTitle=mTitles.get(position);
        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.mView);
        List getTitleonBind1=getTitle(position);
        String getTitleonBind2=getTitleonBind1.get(0).toString();
        final String[] getTitleonBind3=getTitleonBind2.split(",");
        final String getTitleonBind=getTitleonBind3[2];
        final int size=getTitleonBind3.length;
        holder.mTitles.setText(imageTitle);
        holder.mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i=0;
                while(i<size) {
                    Log.d("clickedTitle", getTitleonBind3[i]);
                    Gallery_InsidePage gip =new Gallery_InsidePage();
                    gip.populateDBimages(getTitleonBind3);
                    i++;
                }
               /* Animation slide_up = AnimationUtils.loadAnimation(mContext,
                    R.anim.slide_up);
                holder.itemView.startAnimation(slide_up);*/
            }
        });

    }


    public List getTitle(int pos){
        final String imageTitle=mTitles.get(pos);
        GtableMethods ob2=new GtableMethods();
        List ar=ob2.readContentFromTitle(imageTitle);
        return ar;
    }


    @Override
    public int getItemCount() {int count = 0;if(mDatas != null)count = mDatas.size();return count;}

    static class MyHolder extends RecyclerView.ViewHolder {
        final ImageView mView;
        final TextView mTitles;
        Button mChange;

        MyHolder(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.iv_photo);
            mTitles=itemView.findViewById(R.id.gallery_titles);
            mChange = itemView.findViewById(R.id.fab_change);
        }
    }


//    public int getResId(int position) {
//        return mDatas == null ? 0 : mDatas.get(position);
//    }

    public void setOnItemPhotoChangedListener(OnItemPhotoChangedListener mOnItemPhotoChangedListener) {
        this.mOnItemPhotoChangedListener = mOnItemPhotoChangedListener;
    }

    public interface OnItemPhotoChangedListener {
        void onItemPhotoChanged();
    }

    private class TAG {
    }
}