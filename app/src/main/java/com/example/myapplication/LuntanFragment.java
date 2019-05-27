package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LuntanFragment extends Fragment implements AdapterView.OnItemClickListener,
        MyListView.LoadListener{

    //侧拉菜单控件
    private DrawerLayout drawerLayout;
    private ListView list_left_drawer;
    private ArrayList<DrawerItem> menuLists;
    private MyAdapter<DrawerItem> myAdapter = null;

    //主界面控件
    private MyListView list_content;
    private List<LuntanItem> mainData;
    private MyAdapter<LuntanItem> mainAdapter = null;

    //顶部控件
    private ImageView topLeftIcon;
    private TextView answerTextView;
    private TextView askTextView;

    public LuntanFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.drawerlayout_luntan, container, false);
        bindViews(view);

        onPressView();
        createMainInterface();
        createCeLaMenu();

//        list_left_drawer.setOnItemClickListener(this);
        list_content.setInterface(this);
        return view;
    }

    //按钮弹出侧拉菜单
    public void myOpenLeftDrawer() {
        drawerLayout.openDrawer(Gravity.START);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.START);    //解除锁定
    }

    //绑定控件
    private void bindViews(View view) {
        //查找View
        topLeftIcon = (ImageView) view.findViewById(R.id.luntan_top_img_left);
        answerTextView  = (TextView) view.findViewById(R.id.luntan_ask);
        askTextView = (TextView) view.findViewById(R.id.luntan_anwser);

        //主界面ListView
        list_content =(MyListView) view.findViewById(R.id.luntan_content_list);

        //侧拉菜单listView
        list_left_drawer = (ListView) view.findViewById(R.id.list_left_drawer);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
    }

    //创建主界面的ListView
    private void createMainInterface() {
        mainData = new ArrayList<LuntanItem>();
        mainData.add(new LuntanItem(R.mipmap.mine_photo, "小红", "为什么", 34, 89));

        mainAdapter = new MyAdapter<LuntanItem>( (ArrayList)mainData, R.layout.luntan_item_content) {
            @Override
            public void bindView(ViewHolder holder, LuntanItem obj) {
                holder.setImageResource(R.id.luntan_user_photo, obj.getHeadPortraitId());
                holder.setText(R.id.luntan_user_id, obj.getUserName());
                holder.setText(R.id.luntan_comment_number, obj.getCommentNumber());
                holder.setText(R.id.luntan_content_text, obj.getContentText());
                holder.setText(R.id.luntan_read_number, obj.getReadNumber());
            }
        };
        list_content.setAdapter(mainAdapter);
    }

    //创建侧拉菜单的ListView
    private void createCeLaMenu() {
        menuLists = new ArrayList<DrawerItem>();
        //侧拉菜单栏菜单
        menuLists.add(new DrawerItem("交友"));
        menuLists.add(new DrawerItem("拼车"));
        menuLists.add(new DrawerItem("租房"));
        menuLists.add(new DrawerItem("失物"));
        menuLists.add(new DrawerItem("学习"));
        menuLists.add(new DrawerItem("吃喝玩乐"));
        menuLists.add(new DrawerItem("其他"));

        myAdapter = new MyAdapter<DrawerItem>(menuLists, R.layout.list_luntan_drawer) {
            @Override
            public void bindView(ViewHolder holder, DrawerItem obj) {
                holder.setText(R.id.txt_content, obj.getContent());
            }
        };
        list_left_drawer.setAdapter(myAdapter);
    }

    //绑定按钮
    private void onPressView() {
        //侧拉菜单按钮
        topLeftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOpenLeftDrawer();
            }
        });

        //发帖按钮
        askTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        Bundle bun = new Bundle();
                        bun.putString("luntan", "login test");
                        LoginInterceptor.interceptor(getContext(), "POST_MESSAGE", null);
                    }
                }.start();
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        ContenFragment contenFragment = new ContenFragment();
        Bundle args = new Bundle();
        args.putString("text", menuLists.get(position).getContent());
//        contenFragment.setArguments(args);
        //      FragmentManager fm = getFragmentManager();
        //      fm.beginTransaction().replace(R.id.ly_content, contenFragment).commit();
        drawerLayout.closeDrawer(list_left_drawer);
    }

    @Override
    public void onLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mainAdapter.getCount() < 30) {
                    mainData.add(new LuntanItem(R.mipmap.mine_photo, "小红",
                            "为什么", 34, 89));
                    mainAdapter.notifyDataSetChanged();
                    list_content.loadComplete();
                } else {
                    list_content.haveNoData();
                }
            }
        }, 1000);
    }

}
