package diplomatssummit.com.diplomatssummit.app_ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
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

import diplomatssummit.com.diplomatssummit.Gallery.Gallery_InsidePage;
import diplomatssummit.com.diplomatssummit.R;
import diplomatssummit.com.diplomatssummit.app_ui.util.DLog;
import diplomatssummit.com.diplomatssummit.databases.DbFlow;
import diplomatssummit.com.diplomatssummit.databases.GtableMethods;
import diplomatssummit.com.diplomatssummit.homepage.Home;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private final String TAG =this.getClass().getSimpleName(); ;
    private Context mContext;
    private List<String> mDatas;
    private List<String> mTitles;
    private List<String> mContent;
    private int flag=0;

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

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager=new FragmentManager() {
            @Override
            public FragmentTransaction beginTransaction() {
                return null;
            }

            @Override
            public boolean executePendingTransactions() {
                return false;
            }

            @Override
            public Fragment findFragmentById(int id) {
                return null;
            }

            @Override
            public Fragment findFragmentByTag(String tag) {
                return null;
            }

            @Override
            public void popBackStack() {

            }

            @Override
            public boolean popBackStackImmediate() {
                return false;
            }

            @Override
            public void popBackStack(String name, int flags) {

            }

            @Override
            public boolean popBackStackImmediate(String name, int flags) {
                return false;
            }

            @Override
            public void popBackStack(int id, int flags) {

            }

            @Override
            public boolean popBackStackImmediate(int id, int flags) {
                return false;
            }

            @Override
            public int getBackStackEntryCount() {
                return 0;
            }

            @Override
            public BackStackEntry getBackStackEntryAt(int index) {
                return null;
            }

            @Override
            public void addOnBackStackChangedListener(OnBackStackChangedListener listener) {

            }

            @Override
            public void removeOnBackStackChangedListener(OnBackStackChangedListener listener) {

            }

            @Override
            public void putFragment(Bundle bundle, String key, Fragment fragment) {

            }

            @Override
            public Fragment getFragment(Bundle bundle, String key) {
                return null;
            }

            @Override
            public List<Fragment> getFragments() {
                return null;
            }

            @Override
            public Fragment.SavedState saveFragmentInstanceState(Fragment f) {
                return null;
            }

            @Override
            public boolean isDestroyed() {
                return false;
            }

            @Override
            public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks cb, boolean recursive) {

            }

            @Override
            public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks cb) {

            }

            @Override
            public Fragment getPrimaryNavigationFragment() {
                return null;
            }

            @Override
            public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {

            }

            @Override
            public boolean isStateSaved() {
                return false;
            }
        };
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.rl_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        DLog.d(TAG, "RecyclerAdapter onBindViewHolder" + "--> position = " + position);
        String imageUrl = mDatas.get(position);
        final String imageTitle=mTitles.get(position);
        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.mView);
        List getTitleonBind1=getTitle(position);
        String getTitleonBind2=getTitleonBind1.get(0).toString();
        final String[] getTitleonBind3=getTitleonBind2.replaceAll("-225x300","").split(",");
        final String getTitleonBind=getTitleonBind3[2];
        final int size=getTitleonBind3.length;
        holder.mTitles.setText(imageTitle);
        final ArrayList<String> place=new ArrayList<>();

        holder.mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                place.clear();
                int i=0;
                while(i<size) {
                    Log.d("clickedTitle", getTitleonBind3[i]);
                    place.add(getTitleonBind3[i]);
                    i++;
                }
                Gallery_InsidePage gip =new Gallery_InsidePage();
                gip.setImagelist(place);

                setclickval(holder,1,place);
                /**/
               /* Animation slide_up = AnimationUtils.loadAnimation(mContext,
                    R.anim.slide_up);
                holder.itemView.startAnimation(slide_up);*/
            }
        });

    }



    private void setclickval(MyHolder holder, int i, ArrayList<String> place) {
        Animation slide_up = AnimationUtils.loadAnimation(mContext,
                R.anim.zoom_in);

//        holder.itemView.startAnimation(slide_up);
        ImageView iv2=holder.itemView.findViewById(R.id.iv_photo);
//        iv2.startAnimation(slide_up);
        if(flag<place.size()) {
            Picasso.get().load(place.get(flag)).centerCrop().fit().into(iv2);
            flag++;
        }

        else flag=0;
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