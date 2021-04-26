package com.dadao.mvvm.repository;

import androidx.annotation.NonNull;

import com.dadao.mvvm.bean.User;
import com.dadao.mvvm.net.StateLiveData;
import com.dadao.mvvm.utils.DL;

import java.util.Random;

/**
 * @author dadao on 4/25/21
 */
public class UserRepository {

    private final String TAG = "<--" + this.getClass().getSimpleName() + "-->";


    /**
     * 获取数据
     *
     * @param user
     */
    public void loadUser(@NonNull StateLiveData<User> user) {

        DL.d(TAG, "loadUser");

        User u = new User();
        u.setId("001");
        u.setName("dadao");
        u.setAge(28);
        u.setAvatar("");
        //user.setValue(u);

        user.postBefore();
        new Thread(() -> {

            try {

                Thread.sleep(100);

                u.setAge(new Random().nextInt(100));
                user.postFinish();
                user.postValue(u);

            } catch (InterruptedException e) {
                e.printStackTrace();
                user.postError();
            }

        }).start();

    }

}
