package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeContentFragment extends Fragment implements View.OnClickListener,
            MyListView.LoadListener{

    private MyListView list_one;
    private MyAdapter<HomeRecentNews> myAdapter = null;
    //private ListView list_one = null;
    private List<HomeRecentNews> mData = null;
    private Context mContext = null;
    private int flag = 0;
    private ImageView btn_add;
    private WebView webView;
    private LayoutInflater mInflater;

    public HomeContentFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fg_home, container, false);
        mInflater = inflater;
        list_one = (MyListView) view.findViewById(R.id.list_test);
        btn_add = (ImageView) view.findViewById(R.id.home_top_photo);

        btn_add.setOnClickListener(this);
        setListViewListener(list_one);
        mContext = this.getActivity();
        mData = new ArrayList<HomeRecentNews>();

        myAdapter = new MyAdapter<HomeRecentNews>( (ArrayList)mData, R.layout.item_home_recentnews) {
            @Override
            public void bindView(ViewHolder holder, HomeRecentNews obj) {
                holder.setText(R.id.home_recentnews_title, obj.getTitle());
                holder.setDate(R.id.home_recentnews_date, obj.getDate());
            }
        };
        list_one.setAdapter(myAdapter);
        list_one.setInterface(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId() ) {
            case R.id.home_top_photo:
                myAdapter.add(new HomeRecentNews("2017-2018学年第2期学生足球、篮球、" +
                        "排球、乒乓球、网球、羽毛球、武术、跆拳道、健身气功师比赛规程",
                        new int[]{2016, 12, 9}));
                ++flag;
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (myAdapter.getCount() < 30) {
                    mData.add(new HomeRecentNews("2017-2018学年第2期学生足球、篮球、" +
                            "排球、乒乓球、网球、羽毛球、武术、跆拳道、健身气功师比赛规程",
                            new int[]{2016, 12, 9}));
                    myAdapter.notifyDataSetChanged();
                    list_one.loadComplete();
                } else {
                    list_one.haveNoData();
                }
            }
        }, 1000);
    }

    private void setListViewListener(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new Thread() {
                    @Override
                    public void run() {
                        Intent intent = new Intent( getActivity(), WebViewActivity.class);
                        startActivity(intent);
                    }
                }.start();
            }
        });
    }
}
