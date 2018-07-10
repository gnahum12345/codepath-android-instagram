package me.gnahum12345.instagram;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NonScrollableViewPage extends ViewPager {
    private boolean enabled;

    public NonScrollableViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void scrollTo(int x, int y) {
        if(enabled) {
            super.scrollTo(x, y);
        }
    }
}
