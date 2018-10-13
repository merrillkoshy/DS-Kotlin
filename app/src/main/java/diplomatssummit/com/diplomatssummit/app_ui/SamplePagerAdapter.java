package diplomatssummit.com.diplomatssummit.app_ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import diplomatssummit.com.diplomatssummit.R;

public class SamplePagerAdapter extends PagerAdapter {

    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private List<String> imageList;
    private int slideno;

    public SamplePagerAdapter(Context context, List<String> imageList) {

        this.mContext = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {

        int count = 0;

        if(imageList != null)
            count = imageList.size();

        return count;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int position) {

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_sample_pager, null, false);

        String imageUrl = imageList.get(position);
        ImageView pagerImageView = rootView.findViewById(R.id.pager_image_view);
        pagerImageView.setVisibility(View.VISIBLE);
        Picasso.get().load(imageUrl).centerCrop().fit().into(pagerImageView);

        viewGroup.addView(rootView);


        return rootView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
