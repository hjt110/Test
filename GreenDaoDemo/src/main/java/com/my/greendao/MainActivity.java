package com.my.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.my.greendao.db.bean.User;
import com.my.greendao.db.helper.UserDBHelper;
import com.my.greendao.db.iml.UserAccount;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    private void test() {
        User user = new User();
        user.setAge(26);
        user.setName("xiaoming");
        UserDBHelper.getInstance().updateUserInfo(user);
    }
}
