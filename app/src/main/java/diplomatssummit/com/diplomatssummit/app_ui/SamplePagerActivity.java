package diplomatssummit.com.diplomatssummit.app_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import diplomatssummit.com.diplomatssummit.R;

public class SamplePagerActivity extends AppCompatActivity {

//    @BindView(R.id.sample_pager)
    ViewPager samplePager;

    private List<String> imageList;
    private int slideno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        initialSettings();

        initializeWidgets();
    }

    public void distribution(){
        initialSettings();
    }

    public void pagerdistrib(){
        initializeWidgets();
    }

    private void initialSettings() {

//        ButterKnife.bind(this);

        String imageUrl = "https://raw.githubusercontent.com/JakeWharton/butterknife/master/website/static/logo.png";
        imageList = new ArrayList<>(Arrays.asList(imageUrl, imageUrl, imageUrl, imageUrl, imageUrl));

    }

    private void initializeWidgets() {


        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(this, imageList);
        samplePager.setAdapter(pagerAdapter);

    }
}