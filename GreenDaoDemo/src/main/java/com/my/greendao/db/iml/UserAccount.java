package com.my.greendao.db.iml;

import com.my.greendao.db.bean.User;
import com.my.greendao.db.helper.UserDBHelper;

public class UserAccount {

    private String name;
    private int age;

    private static UserAccount mInstance;
    private final User user;

    private UserAccount(){
        user = UserDBHelper.getInstance().getUserInfo();
    }

    public static UserAccount getInstance(){
        if (mInstance==null){
            mInstance = new UserAccount();
        }
        return mInstance;
    }

    public void init(){
        if (user==null){
            return;
        }
        setName(user.getName());
        setAge(user.getAge());
    }

    public void save(){
        user.setName(name);
        user.setAge(age);
        UserDBHelper.getInstance().updateUserInfo(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
