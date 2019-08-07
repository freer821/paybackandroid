package io.github.project_travel_mate.destinations.description;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.project_travel_mate.R;
import objects.City;
import utils.ExpandableTextView;

import static utils.Constants.EXTRA_MESSAGE_CITY_OBJECT;

public class FinalShopInfoActivity extends AppCompatActivity
        implements View.OnClickListener, FinalCityInfoView {

    @BindView(R.id.layout_content)
    LinearLayout content;
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.shopname)
    TextView shopname;
    @BindView(R.id.shop_addr)
    ExpandableTextView shop_addr;
    @BindView(R.id.shop_description)
    ExpandableTextView shop_description;

    private City mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_shop_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mCity = (City) intent.getSerializableExtra(EXTRA_MESSAGE_CITY_OBJECT);

        initUi();
    }

    /**
     * Initialize view items with information
     * received from previous intent
     */
    private void initUi() {

        setTitle(mCity.getNickname());
        shopname.setText(mCity.getNickname());
        shop_addr.setText(mCity.getInterests().get(0));
        shop_description.setText(mCity.getInterests().get(1));

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void parseResult(String iconUrl, int code, String temp, String humidity, String weatherInfo) {

    }

    @Override
    public void parseInfoResult(String description, String latitude, String longitude, ArrayList<String> imagesArray) {

    }

    @Override
    public void networkError() {

    }

    @Override
    public void onClick(View v) {

    }
}
