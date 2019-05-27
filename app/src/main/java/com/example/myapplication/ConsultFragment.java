package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ConsultFragment extends Fragment implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    private RadioGroup rg_tab;
    private RadioButton rb_new;
    private RadioButton rb_hot;
    private ViewPager viewPager;

    private MyFragmentPageAdapter myFragmentPageAdapter;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;

    public ConsultFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fg_mostnew, container, false);
        myFragmentPageAdapter = new MyFragmentPageAdapter(getFragmentManager());
        bindViews(view);
        rb_new.setChecked(true);
        setSeleted();
        rb_new.setSelected(true);
        return view;
    }

    private void bindViews(View view) {
        rg_tab = (RadioGroup) view.findViewById(R.id.consult_rb_channel);
        rb_new = (RadioButton) view.findViewById(R.id.consult_rb_new);
        rb_hot = (RadioButton) view.findViewById(R.id.consult_rb_hot);

        viewPager = (ViewPager) view.findViewById(R.id.consult_vpager);
        viewPager.setAdapter(myFragmentPageAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }


    private void setSeleted() {
        rb_new.setSelected(false);
        rb_hot.setSelected(false);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.consult_rb_new:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.consult_rb_hot:
                viewPager.setCurrentItem(PAGE_TWO);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_new.setChecked(true);
                    setSeleted();
                    rb_new.setSelected(true);
                    break;
                case PAGE_TWO:
                    setSeleted();
                    rb_hot.setSelected(true);
                    rb_hot.setChecked(true);
                    break;
            }
        }
    }
}
