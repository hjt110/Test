package com.my.greendao.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.my.greendao.db.gen.DaoMaster;
import com.my.greendao.db.gen.DaoSession;
import com.my.greendao.db.iml.UserAccount;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    private static DaoSession mReadDaoSession;
    private static DaoSession mWriteDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initDb();
        UserAccount.getInstance().init();
    }

    private void initDb() {
        try {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
            SQLiteDatabase readableDatabase = devOpenHelper.getReadableDatabase();
            SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
            mReadDaoSession = new DaoMaster(readableDatabase).newSession();
            mWriteDaoSession = new DaoMaster(writableDatabase).newSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DaoSession getmReadDaoSession() {
        return mReadDaoSession;
    }

    public static DaoSession getmWriteDaoSession() {
        return mWriteDaoSession;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
