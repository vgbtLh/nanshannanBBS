package com.example.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //UI Object
    private TextView txt_channel;
    private TextView txt_message;
    private TextView txt_better;
    private TextView txt_setting;
    private FrameLayout ly_content;

    //Fragment Object
    public HomeContentFragment fg1;
    public ConsultFragment fg2;
    public LuntanFragment fg3;
    private MineFragment fg4;
    private HeaderFragment hfg1, hfg2, hfg3;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        bindViews();
        txt_channel.performClick();   //模拟一次点击，既进去后选择第一项
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_channel = (TextView) findViewById(R.id.txt_channel);
        txt_message = (TextView) findViewById(R.id.txt_message);
        txt_better = (TextView) findViewById(R.id.txt_better);
        txt_setting = (TextView) findViewById(R.id.txt_setting);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_channel.setOnClickListener(this);
        txt_message.setOnClickListener(this);
        txt_better.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_channel.setSelected(false);
        txt_message.setSelected(false);
        txt_better.setSelected(false);
        txt_setting.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
        if(hfg1 != null)fragmentTransaction.hide(hfg1);
        if(hfg2 != null)fragmentTransaction.hide(hfg2);
        if(hfg3 != null)fragmentTransaction.hide(hfg3);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_channel:
                setSelected();
                txt_channel.setSelected(true);
                if(fg1 == null){
                    fg1 = new HomeContentFragment();
                    fTransaction.add(R.id.ly_content,fg1);
                    hfg1 = new HeaderFragment("南山喃");
                    fTransaction.add(R.id.ly_top_bar, hfg1);
                }else{
                    fTransaction.show(fg1);
                    fTransaction.show(hfg1);
                }
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                if(fg2 == null){
                    fg2 = new ConsultFragment();
                    fTransaction.add(R.id.ly_content,fg2);
                    hfg2 = new HeaderFragment("最新资讯");
                    fTransaction.add(R.id.ly_top_bar, hfg2);
                }else{
                    fTransaction.show(fg2);
                    fTransaction.show(hfg2);
                }
                break;
            case R.id.txt_better:
                setSelected();
                txt_better.setSelected(true);
                if(fg3 == null){
                    fg3 = new LuntanFragment();
                    fTransaction.add(R.id.ly_all,fg3);
                //    hfg3 = new HeaderFragment("论坛", R.drawable.ic_action_sanheng, 0);
                //    fTransaction.add(R.id.ly_top_bar,hfg3);
                }else{
                    fTransaction.show(fg3);
                //    fTransaction.show(hfg3);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if(fg4 == null){
                    fg4 = new MineFragment();
                    fTransaction.add(R.id.ly_all,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }
}
