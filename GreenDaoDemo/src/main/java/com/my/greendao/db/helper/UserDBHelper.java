package com.my.greendao.db.helper;

import com.my.greendao.base.MyApplication;
import com.my.greendao.db.bean.User;
import com.my.greendao.db.gen.UserDao;

import java.util.List;

public class UserDBHelper {

    private static UserDBHelper mInstance;
    private final UserDao mReadUserDao;
    private final UserDao mWriteUserDao;

    private UserDBHelper() {
        mReadUserDao = MyApplication.getmReadDaoSession().getUserDao();
        mWriteUserDao = MyApplication.getmWriteDaoSession().getUserDao();
    }

    public static UserDBHelper getInstance() {
        if (mInstance == null) {
            synchronized (UserDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new UserDBHelper();
                }
            }
        }
        return mInstance;
    }

    public void updateUserInfo(User user) {
        try {
            if (getUserInfo() != null) {
                mWriteUserDao.deleteAll();
            }
            mWriteUserDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserInfo() {
        List<User> list = mReadUserDao.queryBuilder().list();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
