package diplomatssummit.com.diplomatssummit.app_ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import diplomatssummit.com.diplomatssummit.Gallery.Gal_InsidePageActivity;

import diplomatssummit.com.diplomatssummit.R;
import diplomatssummit.com.diplomatssummit.app_ui.util.DLog;
import diplomatssummit.com.diplomatssummit.databases.DbFlow;
import diplomatssummit.com.diplomatssummit.databases.GtableMethods;
import diplomatssummit.com.diplomatssummit.homepage.Home;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class InvAdapter extends RecyclerView.Adapter<InvAdapter.MyHolder> {

    private final String TAG =this.getClass().getSimpleName();
    private Context mContext;
    private List<String> mDatas;
    private List<String> mTitles;
    private List<String> mContent;


    private OnItemPhotoChangedListener mOnItemPhotoChangedListener;


    public InvAdapter(Context mContext, List<String> mDatas,List<String> mTitles,List<String> mContent) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mTitles=mTitles;
        this.mContent=mContent;
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

        holder.mTitles.setText(imageTitle);

        /*Animation animation,imageanim;
        animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_up);
        imageanim = AnimationUtils.loadAnimation(mContext,R.anim.image_slideup);
        holder.mTitles.startAnimation(animation);
        holder.mView.startAnimation(imageanim);*/
        final ArrayList<String> place=new ArrayList<>();
        holder.mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation,animat2;
                animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_up);
                animat2= AnimationUtils.loadAnimation(mContext,R.anim.image_slideup);
                holder.mTitles.startAnimation(animat2);
                holder.inDesc.setVisibility(View.VISIBLE);
                holder.inDesc.setText(mContent.get(position));
                holder.inDesc.startAnimation(animation);
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
        final TextView inDesc;

        Button mChange;

        MyHolder(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.iv_photo);
            mTitles=itemView.findViewById(R.id.gallery_titles);
            mChange = itemView.findViewById(R.id.fab_change);
            inDesc=itemView.findViewById(R.id.inDesc);

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