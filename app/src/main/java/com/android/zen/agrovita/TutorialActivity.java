package com.android.zen.agrovita;

/**
 * Created by Lenovo on 18.11.2017.
 */
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Step> steps;
    private StepPagerAdapter adapter;

    public ViewPager pager;
    public Button next, prev;
    public LinearLayout indicatorLayout;
    public FrameLayout containerLayout;
    public RelativeLayout buttonContainer;

    public int currentItem;

    public String prevText, nextText, finishText, cancelText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.TutorialStyle);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial);
        steps = new ArrayList<>();
        initTexts();
        initViews();
        initAdapter();
    }

    public void initTexts() {
        prevText = "Back";
        cancelText = "Cancel";
        finishText = "Finish";
        nextText = "Next";
    }

    public void initAdapter() {
        adapter = new StepPagerAdapter(getSupportFragmentManager(), steps);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                controlPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeStatusBarColor(int backgroundColor) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(backgroundColor);
    }

    public void controlPosition(int position) {
        notifyIndicator();
        if (position == steps.size() - 1) {
            next.setText(finishText);
            prev.setText(prevText);
            //startActivity(new Intent(TutorialActivity.this,MainActivity.class));
        } else if (position == 0) {
            prev.setText(cancelText);
            next.setText(nextText);
        } else {
            prev.setText(prevText);
            next.setText(nextText);
        }

        containerLayout.setBackgroundColor(steps.get(position).getBackgroundColor());
        buttonContainer.setBackgroundColor(steps.get(position).getBackgroundColor());
    }

    public void initViews() {
        currentItem = 0;

        pager = (ViewPager) findViewById(R.id.viewPager);
        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);
        indicatorLayout = (LinearLayout) findViewById(R.id.indicatorLayout);
        containerLayout = (FrameLayout) findViewById(R.id.containerLayout);
        buttonContainer = (RelativeLayout) findViewById(R.id.buttonContainer);

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
    }

    public void addFragment(Step step) {
        steps.add(step);
        adapter.notifyDataSetChanged();
        notifyIndicator();
        controlPosition(currentItem);
    }

    public void addFragment(Step step, int position) {
        steps.add(position, step);
        adapter.notifyDataSetChanged();
        notifyIndicator();
    }

    public void notifyIndicator() {
        if (indicatorLayout.getChildCount() > 0)
            indicatorLayout.removeAllViews();

        for (int i = 0; i < steps.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setPadding(8, 8, 8, 8);
            int drawable = R.drawable.circle_black;
            if (i == currentItem)
                drawable = R.drawable.circle_white;

            imageView.setImageResource(drawable);

            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFragment(finalI);
                }
            });

            indicatorLayout.addView(imageView);
        }

    }

    @Override
    public void onBackPressed() {
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            changeFragment(false);
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void changeFragment(int position) {
        pager.setCurrentItem(position, true);
    }

    public void changeFragment(boolean isNext) {
        int item = currentItem;
        if (isNext) {
            item++;
        } else {
            item--;
        }

        if (item < 0 || item == steps.size()) {
            finish();
        } else
            pager.setCurrentItem(item, true);
    }

    public void setPrevText(String text) {
        prevText = text;
    }

    public void setNextText(String text) {
        nextText = text;
    }

    public void setFinishText(String text) {
        finishText = text;
    }

    public void setCancelText(String text) {
        cancelText = text;
    }

}