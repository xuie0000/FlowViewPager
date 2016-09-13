package com.xuie.flowviewpager.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by mdw on 2016/4/20.
 */
public class DimenUtils {
    public static int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
