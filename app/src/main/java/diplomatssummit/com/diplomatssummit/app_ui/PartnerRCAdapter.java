package diplomatssummit.com.diplomatssummit.app_ui;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import diplomatssummit.com.diplomatssummit.R;
import diplomatssummit.com.diplomatssummit.app_ui.util.DLog;

import static android.content.ContentValues.TAG;


public class PartnerRCAdapter extends RecyclerView.Adapter<PartnerRCAdapter.MyHolderP> {
    private Context mContext;
    private List<String> mDatas;

    private OnItemPhotoChangedListener mOnItemPhotoChangedListener;


    public PartnerRCAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyHolderP onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.partner_item, parent, false);
        return new MyHolderP(itemView);
    }



    @Override
    public void onBindViewHolder(final MyHolderP holder, final int position) {

        DLog.d(TAG, "RecyclerAdapter onBindViewHolder" + "--> position = " + position);
        String imageUrl = mDatas.get(position);
        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.mView);
        }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }



    static class MyHolderP extends RecyclerView.ViewHolder {
        final ImageView mView;
        Button mChange;

        MyHolderP(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.iv_photo);
            mChange = itemView.findViewById(R.id.fab_change);
        }
    }


    /*public int getResId(int position) {
        return mDatas == null ? 0 : mDatas.get(position);
    }*/

    public void setOnItemPhotoChangedListener(OnItemPhotoChangedListener mOnItemPhotoChangedListener) {
        this.mOnItemPhotoChangedListener = mOnItemPhotoChangedListener;
    }

    public interface OnItemPhotoChangedListener {
        void onItemPhotoChanged();
    }
}
