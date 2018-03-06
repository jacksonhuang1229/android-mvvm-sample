package com.example.jacksonh.android_mvvm_sample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jacksonh.android_mvvm_sample.activity.UserInfoActivity;
import com.example.jacksonh.android_mvvm_sample.databinding.ActivityMainBinding;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("Test", "User");
        binding.setUser(user);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_user_info)
    void onClickButton() {
        try {
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
