package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyListView extends ListView implements AbsListView.OnScrollListener {
    private View bottomView;
    private int totalItemCounts;
    private int lastVisible;
    private LoadListener loadListener;  //
    private int bottomHeight;
    private int YLoad; //位置
    boolean isLoading;  //加载状态
    private TextView headtxt;
    private TextView headtime;
    private ProgressBar progressBar;

    private TextView bottomText;

    public MyListView(Context context) {
        super(context);
        initView(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //拿到布局文件
        bottomView = LinearLayout.inflate(context, R.layout.list_bottom, null);
        bottomText = bottomView.findViewById(R.id.txt);
        progressBar = bottomView.findViewById(R.id.bottom_progress_bar);
        //测量底部文件高度
        bottomView.measure(0, 0);
        //得到高度
        bottomHeight = bottomView.getMeasuredHeight();
        //隐藏view
        bottomView.setPadding(0, -bottomHeight, 0, 0);
        this.addFooterView(bottomView);
        this.setOnScrollListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                YLoad = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(ev);
    }


    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCounts == lastVisible && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                bottomView.setPadding(0, 0, 0, 0);
                //加载数据

                loadListener.onLoad();
            }
        }
    }

    public interface LoadListener {
        void onLoad();
     //   void PullLoad();
    }

    @Override
    public void onScroll(AbsListView view, int fistVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisible = fistVisibleItem + visibleItemCount;
        this.totalItemCounts = totalItemCount;
    }

    //加载完成
    public void loadComplete() {
        isLoading = false;
        bottomText.setText("加载中......");
        bottomView.setPadding(0, -bottomHeight, 0, 0);
    }

    //没有数据了
    public void haveNoData() {
        isLoading = false;
        progressBar.setPadding(0, -bottomHeight, 0, 0);
        bottomText.setText("没有更多了");
        bottomView.setPadding(0, 0, 0, 0);
    }

    public void setInterface(LoadListener loadListener) {
        this.loadListener = loadListener;
    }

    //显示多行
   /* @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }*/
}
