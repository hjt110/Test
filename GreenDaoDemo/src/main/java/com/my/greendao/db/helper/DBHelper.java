package com.my.greendao.db.helper;

import com.my.greendao.base.MyApplication;
import com.my.greendao.db.GreenDaoManager;
import com.my.greendao.db.bean.User;
import com.my.greendao.db.gen.DaoSession;
import com.my.greendao.db.gen.UserDao;

public class DBHelper {

    private static DBHelper mInstance;
    private final DaoSession mReadDaoSession;
    private final DaoSession mWriteDaoSession;

    private DBHelper(){
        mReadDaoSession = MyApplication.getmReadDaoSession();
        mWriteDaoSession = MyApplication.getmWriteDaoSession();
    }

    public static DBHelper getInstance(){
        if (mInstance == null){
            mInstance = new DBHelper();
        }
        return mInstance;
    }

}
