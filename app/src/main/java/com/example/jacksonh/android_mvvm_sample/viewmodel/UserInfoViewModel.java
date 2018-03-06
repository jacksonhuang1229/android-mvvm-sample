package com.example.jacksonh.android_mvvm_sample.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.example.jacksonh.android_mvvm_sample.BR;
import com.example.jacksonh.android_mvvm_sample.R;
import com.example.jacksonh.android_mvvm_sample.User;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gameg on 2018/3/6.
 */

public class UserInfoViewModel extends ViewModel {
    private final Context appContext;
    private User mUser;
    private Drawable mDrawable;

    public UserInfoViewModel(Context context, @Nullable State savedInstanceState) {
        super(savedInstanceState);
        appContext = context.getApplicationContext();
        loadUserInfo();
    }

    private void loadUserInfo(){

        Single.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                try{
                    Thread.sleep(3000); // load 3 sec
                }catch (Exception e){
                    // nothing
                }
                User user = new User("test" , "user");
                return user;
            }
        }).subscribeOn(Schedulers.io())
                .flatMap(new Function<User, SingleSource<Drawable>>() {
                    @Override
                    public SingleSource<Drawable> apply(User user) throws Exception {
                        mUser = user;
                        notifyPropertyChanged(BR.firstName);
                        notifyPropertyChanged(BR.lastName);

                        try{
                            Thread.sleep(3000); // load 3 sec
                        }catch (Exception e){
                            // nothing
                        }

                        Drawable drawable = appContext.getResources().getDrawable(R.drawable.dog01);
                        return Single.just(drawable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Drawable>() {
                    @Override
                    public void accept(Drawable drawable) throws Exception {
                        mDrawable = drawable;
                        notifyPropertyChanged(BR.image);
                        notifyPropertyChanged(BR.showProgress);
                    }
                });

    }


    /***
         * method name must be getFirstName()  , can't  be firstName()
         * @return
         */
    @Bindable
    public String getFirstName(){
        return mUser == null ? " loading " : mUser.getFirstName();
    }
    @Bindable
    public String getLastName(){
        return mUser == null ? " loading " : mUser.getLastName();
    }

    @Bindable
    public Drawable getImage(){
        return mDrawable;
    }

    /***
     * method name must be isShowProgress()  , can't  be showProgress()
     * @return
     */
    @Bindable
    public boolean isShowProgress(){
        return mDrawable == null ;
    }
}
