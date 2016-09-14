package com.xuie.flowviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xuie.flowviewpager.util.DimenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuie on 16-9-13.
 */
public class FlowFragmentPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    private static final String TAG = "FlowFragmentPagerAdapte";
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private List<Fragment> fragments;

    private int widthPadding = DimenUtils.dp2px(24);
    private int heightPadding = DimenUtils.dp2px(32);

    public FlowFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    @Override public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override public int getCount() {
        return fragments.size();
    }

    @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.d(TAG, "onPageScrolled: " + position + ", " + positionOffset);
        if (fragments.size() <= 0)
            return;

        if (position > fragments.size())
            return;

        int outHeightPadding = (int) (positionOffset * heightPadding);
        int outWidthPadding = (int) (positionOffset * widthPadding);
        fragments.get(position).getView().setPadding(outWidthPadding, outHeightPadding, outWidthPadding, outHeightPadding);

        if (position < fragments.size() - 1) {
            int inWidthPadding = (int) ((1 - positionOffset) * widthPadding);
            int inHeightPadding = (int) ((1 - positionOffset) * heightPadding);
            fragments.get(position + 1).getView().setPadding(inWidthPadding, inHeightPadding, inWidthPadding, inHeightPadding);
        }
        if (mOnPageChangeListeners != null) {
            for (OnPageChangeListener listener : mOnPageChangeListeners) {
                listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }
    }

    @Override public void onPageSelected(int position) {
        if (position < fragments.size() - 1) {
            View nextView = fragments.get(position + 1).getView();
            if (nextView.getPaddingLeft() == 0)
                nextView.setPadding(widthPadding, heightPadding, widthPadding, heightPadding);
        }

        if (position > 0) {
            View preView = fragments.get(position - 1).getView();
            if (preView.getPaddingLeft() == 0)
                preView.setPadding(widthPadding, heightPadding, widthPadding, heightPadding);
        }

        if (mOnPageChangeListeners != null) {
            for (OnPageChangeListener listener : mOnPageChangeListeners) {
                listener.onPageSelected(position);
            }
        }
    }

    @Override public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListeners != null) {
            for (OnPageChangeListener listener : mOnPageChangeListeners) {
                listener.onPageScrollStateChanged(state);
            }
        }
    }


    public void addOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListeners == null) {
            mOnPageChangeListeners = new ArrayList<>();
        }
        mOnPageChangeListeners.add(listener);
    }

    public void removeOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListeners != null) {
            mOnPageChangeListeners.remove(listener);
        }
    }


    public interface OnPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }
}
