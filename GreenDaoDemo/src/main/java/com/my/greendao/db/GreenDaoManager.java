package com.my.greendao.db;

import com.my.greendao.base.MyApplication;
import com.my.greendao.db.gen.DaoMaster;
import com.my.greendao.db.gen.DaoSession;
import com.my.greendao.db.gen.UserDao;

public class GreenDaoManager {

    private static GreenDaoManager mInstance;
    private final DaoMaster mDaoMaster;
    private final DaoSession mDaoSession;
    private final UserDao mUserDao;

    private GreenDaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(), "notes-db", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getReadableDb());
        mDaoSession = mDaoMaster.newSession();
        mUserDao = mDaoSession.getUserDao();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    public UserDao getmUserDao() {
        return mUserDao;
    }
}
