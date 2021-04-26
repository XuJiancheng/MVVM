package com.dadao.mvvm.core;


import androidx.multidex.MultiDexApplication;

import com.dadao.mvvm.utils.DL;
import com.tencent.mm.hardcoder.HardCoderCallback;
import com.tencent.mm.hardcoder.HardCoderJNI;
import com.tencent.mm.hardcoder.HardCoderLog;

import dagger.hilt.android.HiltAndroidApp;


/**
 * @author dadao
 */
@HiltAndroidApp
public class MyApp extends MultiDexApplication {

    private static MyApp mV1App = null;
    private final String TAG = "||||||||||||" + this.getClass().getSimpleName() + "|| ";

    public static MyApp getInstance() {
        return mV1App;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initHardCoder();

        DL.init("BY_TEST_MVVM");
        mV1App = this;

    }

    private void initHardCoder() {
        new Thread(() -> {
            final String remote = HardCoderJNI.readServerAddr();

            final int ret = HardCoderJNI.initHardCoder(remote, 0, HardCoderJNI.CLIENT_SOCK, null, new HardCoderCallback.ConnectStatusCallback() {

                @Override
                public void onConnectStatus(final boolean isConnect) {
                    HardCoderLog.i(TAG, "initHardCoder callback, isConnectSuccess:" + isConnect);

                    HardCoderJNI.startPerformance(0, HardCoderJNI.CPU_LEVEL_1,
                            HardCoderJNI.IO_LEVEL_1, HardCoderJNI.GPU_LEVEL_1,
                            new int[]{}, 5000, HardCoderJNI.APP_SCENE_STARTUP,
                            0, android.os.Process.myTid(), TAG);

                }
            });
            HardCoderLog.i(TAG, "initHardCoder, server socket name:" + remote);
        }).start();
    }


}

