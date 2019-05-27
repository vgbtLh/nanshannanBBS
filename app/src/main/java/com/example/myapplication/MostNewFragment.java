package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MostNewFragment extends Fragment implements MyListView.LoadListener {
    private TextView textView;
    private MyListView listView;
    private List<LuntanItem> mainData;
    private MyAdapter<LuntanItem> mainAdapter = null;

    public MostNewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fg_consult, container, false);

        listView = (MyListView) view.findViewById(R.id.consult_list);
        mainData = new ArrayList<LuntanItem>();
        mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
        mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
        mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
        mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
        mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
        mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));

        mainAdapter = new MyAdapter<LuntanItem>( (ArrayList)mainData, R.layout.luntan_item_content ) {
            @Override
            public void bindView(ViewHolder holder, LuntanItem obj) {
                holder.setImageResource(R.id.luntan_user_photo, obj.getHeadPortraitId());
                holder.setText(R.id.luntan_user_id, obj.getUserName());
                holder.setText(R.id.luntan_comment_number, obj.getCommentNumber());
                holder.setText(R.id.luntan_content_text, obj.getContentText());
                holder.setText(R.id.luntan_read_number, obj.getReadNumber());
            }
        };
        listView.setInterface(this);
        listView.setAdapter(mainAdapter);
        return view;
    }

    @Override
    public void onLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mainAdapter.getCount() < 30) {
                    mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
                    mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
                    mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
                    mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
                    mainData.add(new LuntanItem(R.mipmap.mine_photo, "最新", "为什么", 34, 89));
                    mainAdapter.notifyDataSetChanged();
                    listView.loadComplete();
                } else {
                    listView.haveNoData();
                }
            }
        }, 1000);
    }
}
