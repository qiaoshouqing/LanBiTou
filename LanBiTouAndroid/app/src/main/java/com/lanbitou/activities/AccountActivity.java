package com.lanbitou.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanbitou.R;
import com.lanbitou.entities.UserEntity;


/**
 * 账户信息
 * Created by Henvealf on 16-5-13.
 */
public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private TextView title_bar_name;
    private TextView name_tv;
    private TextView email_tv;
    private TextView about_tv;
    private TextView logout_tv;
    private Context context = AccountActivity.this;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_account);


        back = (ImageView)findViewById(R.id.back);
        title_bar_name = (TextView)findViewById(R.id.title_bar_name);
        name_tv = (TextView) findViewById(R.id.name_tv);
        email_tv = (TextView) findViewById(R.id.email_tv);
        about_tv = (TextView) findViewById(R.id.about_tv);
        logout_tv = (TextView) findViewById(R.id.logout_tv);

        title_bar_name.setText("LanBiTou");

        preferences = getSharedPreferences("lanbitou", MODE_PRIVATE);
        editor = preferences.edit();

        String userJson = preferences.getString("user", null);
        UserEntity userEntity = gson.fromJson(userJson, UserEntity.class);
        name_tv.setText(userEntity.getName());
        email_tv.setText(userEntity.getEmail());

        back.setOnClickListener(this);
        about_tv.setOnClickListener(this);
        logout_tv.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back :
                finish();
                break;
            case R.id.about_tv :
                Toast.makeText(context, "我们长得很帅", Toast.LENGTH_LONG).show();
                break;
            case R.id.logout_tv :
                editor.putString("user", null);
                editor.putInt("uid", 0);
                editor.commit();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
