package com.dadao.mvvm.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dadao.mvvm.R;

/**
 * @author dadao on 2021-01-04.
 */

public class LoadingDialog extends Dialog {

    private static final int MIN_SHOW_TIME = 500;
    private static final int MIN_DELAY = 200;

    Handler mHandler = new Handler(Looper.getMainLooper());

    long mStartTime = -1;

    boolean mPostedHide = false;
    private final Runnable mDelayedHide = () -> {
        mPostedHide = false;
        mStartTime = -1;
        super.hide();
    };
    boolean mPostedShow = false;
    boolean mDismissed = false;
    private final Runnable mDelayedShow = () -> {
        mPostedShow = false;
        if (!mDismissed) {
            mStartTime = System.currentTimeMillis();
            super.show();
        }
    };

    public LoadingDialog(@NonNull Context context) {
        super(context);
        initViews();
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initViews();
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initViews();
    }

    private void removeCallbacks() {
        mHandler.removeCallbacks(mDelayedHide);
        mHandler.removeCallbacks(mDelayedShow);
    }

    @Override
    public void show() {

        mStartTime = -1;
        mDismissed = false;
        mHandler.removeCallbacks(mDelayedHide);
        mPostedHide = false;
        if (!mPostedShow) {
            mHandler.postDelayed(mDelayedShow, MIN_DELAY);
            mPostedShow = true;
        }
    }

    @Override
    public void hide() {

        mDismissed = true;
        mHandler.removeCallbacks(mDelayedShow);
        mPostedShow = false;
        long diff = System.currentTimeMillis() - mStartTime;
        if (diff >= MIN_SHOW_TIME || mStartTime == -1) {

            super.hide();
        } else {

            if (!mPostedHide) {
                mHandler.postDelayed(mDelayedHide, MIN_SHOW_TIME - diff);
                mPostedHide = true;
            }
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViews() {
        setContentView(R.layout.layout_loading);

        setCanceledOnTouchOutside(false);

        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.dimAmount = 0.0f;

            window.setBackgroundDrawableResource(R.color.transparent);
            window.setAttributes(params);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        }
    }

}