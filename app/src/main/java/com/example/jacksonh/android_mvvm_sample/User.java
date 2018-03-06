package com.example.jacksonh.android_mvvm_sample;

/**
 * Created by jacksonh on 2018/3/2.
 */

public class User {
    public final String firstName;
    public final String lastName;
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
