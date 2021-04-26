package com.dadao.mvvm.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.dadao.mvvm.R;

/**
 * @author dadao on 2021/01/04.
 */
public class LoadingView extends RelativeLayout {

    private static final int MIN_SHOW_TIME = 500;
    private static final int MIN_DELAY = 500;
    private final String TAG = "||||||||||||" + this.getClass().getSimpleName() + "|| ";
    boolean isBgNotTouchable;

    long mStartTime = -1;

    boolean mPostedHide = false;
    boolean mPostedShow = false;
    boolean mDismissed = false;
    private Context mContext;
    private final Runnable mDelayedHide = () -> {
        mPostedHide = false;
        mStartTime = -1;
        setVisibility(View.GONE);

    };
    private final Runnable mDelayedShow = () -> {
        mPostedShow = false;
        if (!mDismissed) {
            mStartTime = System.currentTimeMillis();
            setVisibility(View.VISIBLE);

        }
    };

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.loading,
                0, 0);
        try {
            isBgNotTouchable = a.getBoolean(R.styleable.loading_bgNotTouchable, true);

        } finally {
            a.recycle();
        }

        initView();
    }


    public Activity getActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private void initView() {

        inflate(mContext, R.layout.layout_loading, this);
        post(() -> {
            setVisibility(GONE);
        });

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks();

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    private void removeCallbacks() {
        removeCallbacks(mDelayedHide);
        removeCallbacks(mDelayedShow);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (isBgNotTouchable) {
            switch (visibility) {
                case VISIBLE:

                    if (getActivity(mContext) != null) {
                        getActivity(mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }

                    break;
                case GONE:
                case INVISIBLE:

                    if (getActivity(mContext) != null) {
                        getActivity(mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                    break;

                default:

                    if (getActivity(mContext) != null) {
                        getActivity(mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }

                    break;
            }
        }

    }

    public synchronized void hide() {
        mDismissed = true;
        removeCallbacks(mDelayedShow);
        mPostedShow = false;
        long diff = System.currentTimeMillis() - mStartTime;
        if (diff >= MIN_SHOW_TIME || mStartTime == -1) {

            setVisibility(View.GONE);
        } else {

            if (!mPostedHide) {
                postDelayed(mDelayedHide, MIN_SHOW_TIME - diff);
                mPostedHide = true;
            }
        }
    }

    public synchronized void show() {

        mStartTime = -1;
        mDismissed = false;
        removeCallbacks(mDelayedHide);
        mPostedHide = false;
        if (!mPostedShow) {
            postDelayed(mDelayedShow, MIN_DELAY);
            mPostedShow = true;
        }
    }

    private WindowManager.LayoutParams tryGetWindowParams() {
        View view = this;
        if (view.getLayoutParams() instanceof WindowManager.LayoutParams) {
            return (WindowManager.LayoutParams) view.getLayoutParams();
        }
        while (view.getParent() instanceof View) {
            view = (View) view.getParent();
            if (view.getLayoutParams() instanceof WindowManager.LayoutParams) {
                return (WindowManager.LayoutParams) view.getLayoutParams();
            }
        }
        return null;
    }


    // TODO: 2021/1/5 无效 
    private boolean setWindowNotTouchable() {
        WindowManager.LayoutParams windowParams = tryGetWindowParams();
        if (windowParams != null) {
            if ((windowParams.flags & WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE) == 0) {
                windowParams.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
                requestLayout();
            }
            return true;
        }
        return false;
    }

    private boolean setWindowTouchable() {
        WindowManager.LayoutParams windowParams = tryGetWindowParams();
        if (windowParams != null) {
            if ((windowParams.flags & WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE) != 0) {
                windowParams.flags &= ~WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
                requestLayout();
            }
            return true;
        }
        return false;
    }

}
