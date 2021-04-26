package com.dadao.mvvm.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.dadao.mvvm.BuildConfig;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;


/**
 * Log统一管理类
 *
 * @author dadao
 * @date 2020-11-20
 */
public class DL {
    private static String TAG = "DL";
    private static boolean isDebug = true;

    public static void init() {
        init(null);
    }

    public static void init(String tag) {
        if (TextUtils.isEmpty(tag)) {
            TAG = tag;
        }
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL
                        : LogLevel.NONE)
                .tag(TAG)
                //.enableThreadInfo()
                //.enableStackTrace(2)
                //.enableBorder()
                .build();
        XLog.init(config);
    }

    /**
     * 是否打印日志
     *
     * @param isDebug
     */
    public static void setIsDebug(boolean isDebug) {
        isDebug = isDebug;
    }

    public static void json(JSONObject msg) {
        if (isDebug) {
            XLog.json(msg.toJSONString());
        }
    }

    public static void object(Object msg) {
        if (isDebug) {

            XLog.d(JSONObject.toJSONString(msg));
        }
    }

    /**
     * 默认tag的函数
     *
     * @param msg log内容
     */
    public static void i(String msg) {
        if (isDebug) {
            Logger partial = XLog.tag(TAG)
                    .build();
            partial.i(msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger partial = XLog.tag(TAG)
                    .build();
            partial.d(msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Logger partial = XLog.tag(TAG)
                    .build();
            partial.e(msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Logger partial = XLog.tag(TAG)
                    .build();
            partial.v(msg);
        }
    }

    /**
     * 自定义tag的函数
     *
     * @param tag tag
     * @param msg log内容ø
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Logger partial = XLog.tag(tag)
                    .build();
            partial.i(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Logger partial = XLog.tag(tag)
                    .build();
            partial.d(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Logger partial = XLog.tag(tag)
                    .build();
            partial.e(msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Logger partial = XLog.tag(tag)
                    .build();
            partial.v(msg);
        }
    }
}
