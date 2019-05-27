package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MineFragment extends Fragment implements View.OnClickListener{
    private TextView   mine_txt_log;
    private ImageView  mine_photo_log;

    private String userId;
    private int imgDerectory;

    public MineFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
        View view = inflater.inflate(R.layout.fg_mine, container, false);

        //UI object
        mine_txt_log = (TextView) view.findViewById(R.id.mine_txt_log);
        mine_photo_log = (ImageView) view.findViewById(R.id.mine_photo_log);

        setDrawable(view, R.id.mine_mymessage, 0);
        setDrawable(view, R.id.mine_mytiezi, 0);
        setDrawable(view, R.id.mine_mycollect, 0);
        setDrawable(view, R.id.mine_history, 0);
        setDrawable(view, R.id.mine_aboutus, 0);

        setDrawable(view, R.id.mine_shenghezhong, 1);
        setDrawable(view, R.id.mine_yifabu, 1);
        setDrawable(view, R.id.mine_yishouhui, 1);
        setDrawable(view, R.id.mine_caogaoxiang, 1);

        if (LoginActivity.getLoginState() == true) {
           // userId = saveInstanceState.getString("userId");
            //imgDerectory = saveInstanceState.getInt("userPhotoImg", R.mipmap.mine_photo);
            mine_txt_log.setText("");
           // mine_photo_log.setImageResource(imgDerectory);

            //打开个人信息页面
            mine_photo_log.setClickable(false);
            mine_txt_log.setClickable(false);
        }

        //Bind Event
        mine_txt_log.setOnClickListener(this);
        mine_photo_log.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.mine_photo_log:
            case R.id.mine_txt_log:
                Intent intent = new Intent( getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
             default:
                 break;
        }
    }

    private void setDrawable(View view, int id, int position) {
        TextView textView = (TextView) view.findViewById(id);
        Drawable[] drawables = textView.getCompoundDrawables();
        drawables[position].setBounds(0, 0, 50, 50);
        textView.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }
}
