package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static boolean loginState = false;
    private EditText editName;
    private EditText editPassword;
    private Button btnLogin;
    private String strName;
    private String strPassword;
    private Context mContext;
    private SharedHelper sh;
    private Bundle bundle;

    private LoginCarrier invoker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bundle = this.getIntent().getExtras();

        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        bindViews();
    }

    private void bindViews() {
        editName = (EditText) findViewById(R.id.edit_user);
        editPassword = (EditText) findViewById(R.id.edit_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
}

    @Override
    protected void onStart() {
        super.onStart();
        Map<String, String> data = sh.read();
        editName.setText(data.get("username"));
        editPassword.setText(data.get("password"));
    }

    public void btnClickActLogin(View source) {
        strName = editName.getText().toString();
        strPassword = editPassword.getText().toString();

        if( strName.equals("h123") && strPassword.equals("123") ) {
            setLoginState(true);
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            sh.save(strName, strPassword);

            Intent it = new Intent(mContext, MineFragment.class);
            Bundle bundleToMine = new Bundle();
            bundleToMine.putString("userId", "好难啊~");
            it.putExtras(bundleToMine);

            invoker = (LoginCarrier) getIntent().getParcelableExtra("POST_MESSAGE");
            invoker.invoke(LoginActivity.this);

            finish();

        } else {
            setLoginState(false);
            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLoginState(boolean state) {
        this.loginState = state;
    }

    public static boolean getLoginState() {
        return loginState;
    }

}
