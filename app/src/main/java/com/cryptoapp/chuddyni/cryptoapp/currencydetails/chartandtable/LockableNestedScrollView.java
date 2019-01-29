package com.cryptoapp.chuddyni.cryptoapp.currencydetails.chartandtable;


import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;


//https://stackoverflow.com/questions/5763304/disable-scrollview-programmatically

class LockableNestedScrollView extends NestedScrollView {

    public LockableNestedScrollView(Context context) {
        this(context, null);
    }

    public LockableNestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockableNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean mScrollable = true;

    public void setScrollingEnabled(boolean enabled) {
        mScrollable = enabled;
    }

    public boolean isScrollable() {
        return mScrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mScrollable) return super.onTouchEvent(ev);
                return mScrollable;
            default:
                return super.onTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mScrollable) return false;
        else return super.onInterceptTouchEvent(ev);
    }

}
