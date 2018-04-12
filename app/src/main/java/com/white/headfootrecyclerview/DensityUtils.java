package com.white.headfootrecyclerview;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;


/**
 * Created by MicYun on 2017/12/14.
 */

public class DensityUtils {

  public static float dp2px(float dp) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, MyApplication.getInstance().getResources().getDisplayMetrics());
  }

  public static float sp2px(float sp) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, MyApplication.getInstance().getResources().getDisplayMetrics());
  }

  public static float px2dp(float px) {
    float scale = MyApplication.getInstance().getResources().getDisplayMetrics().density;
    return px / scale;
  }

  public static float px2sp(float px) {
    float scale = MyApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
    return px / scale;
  }

  public static int getScreenWidth() {
    WindowManager wm = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
    int width = wm.getDefaultDisplay().getWidth();
    return width;
  }

  public static int getScreenHeight() {
    WindowManager wm = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
    int height = wm.getDefaultDisplay().getHeight();
    return height;
  }
}
