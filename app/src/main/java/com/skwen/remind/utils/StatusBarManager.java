package com.skwen.remind.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import com.skwen.remind.R;

/**
 * 作者：vicent
 * 时间：2019/1/9
 */
public class StatusBarManager {

    public static final int DEFAULT_STATUS_BAR_ALPHA = 0;

    /**
     * 设置状态栏颜色
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     */
    public static void setColor(Activity activity, int color) {
        setColor(activity, color, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity              需要设置的activity
     * @param color                 状态栏颜色值
     * @param defaultStatusBarAlpha 状态栏透明度
     */
    private static void setColor(Activity activity, int color, int defaultStatusBarAlpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //Then call setStatusBarColor.
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //activity.getWindow().setStatusBarColor(calculateStatusColor(color, statusBarAlpha));
            activity.getWindow().setStatusBarColor(color);
        }
    }

    public static void setLightMode(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 设置状态栏底色白色
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(activity.getResources().
                    getColor(R.color.background_color, null));

            // 设置状态栏字体黑色
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}
