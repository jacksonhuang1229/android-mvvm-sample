package com.example.jacksonh.android_mvvm_sample.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jacksonh.android_mvvm_sample.R;
import com.example.jacksonh.android_mvvm_sample.databinding.ActivityUserInfoBinding;
import com.example.jacksonh.android_mvvm_sample.viewmodel.UserInfoViewModel;
import com.example.jacksonh.android_mvvm_sample.viewmodel.ViewModel;

import butterknife.ButterKnife;

/**
 * Created by gameg on 2018/3/6.
 */

public class UserInfoActivity extends ViewModelActivity {

    private UserInfoViewModel userInfoViewModel;

    @Nullable
    @Override
    protected ViewModel createViewModel(@Nullable ViewModel.State savedViewModelState) {
        userInfoViewModel = new UserInfoViewModel(this, savedViewModelState);
        return userInfoViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserInfoBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        binding.setViewmodel(userInfoViewModel);
        ButterKnife.bind(this);
    }
}
