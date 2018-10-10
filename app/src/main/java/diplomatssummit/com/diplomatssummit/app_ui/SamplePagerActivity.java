package diplomatssummit.com.diplomatssummit.app_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import diplomatssummit.com.diplomatssummit.R;

public class SamplePagerActivity extends AppCompatActivity {

//    @BindView(R.id.sample_pager)
    ViewPager samplePager;

    private List<String> imageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_pager);

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
        samplePager=findViewById(R.id.sample_pager);
        String imageUrl = "https://raw.githubusercontent.com/JakeWharton/butterknife/master/website/static/logo.png";
        imageList = new ArrayList<>(Arrays.asList(imageUrl, imageUrl, imageUrl, imageUrl, imageUrl));

    }

    private void initializeWidgets() {

        samplePager=findViewById(R.id.sample_pager);
        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(this, imageList);
        samplePager.setAdapter(pagerAdapter);

    }
}