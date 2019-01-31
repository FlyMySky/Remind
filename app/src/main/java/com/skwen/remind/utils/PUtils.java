package com.skwen.remind.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

/**
 * 作者：vicent
 * 时间：2019/1/31
 */
public class PUtils {

    public static int REQUEST_IGNORE_BATTERY_CODE = 2001;


    private static class Holder {
        private static PUtils instance = new PUtils();
    }

    private PUtils() {

    }

    public static PUtils getInstance() {
        return Holder.instance;
    }

    public boolean isIgnoringBatteryOptimizations(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String packageName = context.getPackageName();
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            return pm.isIgnoringBatteryOptimizations(packageName);
        }
        return false;
    }


    public void gotoSettingIgnoringBatteryOptimizations(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                Intent intent = new Intent();
                String packageName = activity.getPackageName();
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                activity.startActivityForResult(intent, REQUEST_IGNORE_BATTERY_CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
