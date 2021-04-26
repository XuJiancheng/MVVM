package com.dadao.mvvm.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.StringRes;


/**
 * Created by XuJiancheng on 2017/5/14.
 */

public class DT {
    // Toast
    private static Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (null == toast) {
            toast = initToast(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (null == toast) {
            toast = initToast(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);

            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showOneShort(Context context, int message) {

        Toast t = initToast(context, message, Toast.LENGTH_SHORT);
        t.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (null == toast) {
            toast = initToast(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (null == toast) {
            toast = initToast(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setText(message);
        }
        toast.show();
    }


    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (null == toast) {
            toast = initToast(context, message, duration);
        } else {
            toast.setDuration(duration);
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (null == toast) {
            toast = initToast(context, message, duration);
        } else {
            toast.setDuration(duration);
            toast.setText(message);
        }
        toast.show();
    }


    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }

    public static Toast initToast(Context context, CharSequence msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(msg);
        toast.setDuration(duration);

        return toast;
    }

    public static Toast initToast(Context context, @StringRes int resId, int duration) {
        Toast toast = Toast.makeText(context, resId, duration);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(resId);
        toast.setDuration(duration);

        return toast;
    }


}
