package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HeaderFragment extends Fragment implements View.OnClickListener{
    MainActivity activity = (MainActivity)getActivity();

    private TextView title = null;
    private ImageView left = null;
    private ImageView right = null;

    private int leftImgId = 0;
    private int rightImgId = 0;
    private String content;

    public HeaderFragment() {
        content = "";
    }

    public HeaderFragment(String content, int leftImgId, int rightImgId) {
        this.content = content;
        this.leftImgId = leftImgId;
        this.rightImgId = rightImgId;
    }

    public HeaderFragment(String content) {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fg_top, container, false);
        title = (TextView) view.findViewById(R.id.txt_top_title);
        title.setText(content);

        if(leftImgId != 0){
            left = (ImageView) view.findViewById(R.id.top_img_left);
            left.setImageResource( leftImgId );
        }

        if(leftImgId != 0){
            right = (ImageView) view.findViewById(R.id.top_img_right);
            right.setImageResource(rightImgId);
        }

        bindViews();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_img_left:
                if(activity.fg3 != null)
                    activity.fg3.myOpenLeftDrawer();
                break;

            case R.id.top_img_right:
                break;

            default:
                break;
        }
    }

    private void bindViews() {
        if(left != null)
            left.setOnClickListener(this);

        if(right != null)
            right.setOnClickListener(this);
    }
}
