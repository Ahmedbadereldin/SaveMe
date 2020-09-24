package com.serveme.savemyphone.view.utils;

import java.io.IOException;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import com.serveme.savemyphone.view.UserView;

public class AlertUtility {

    private static View thisView;

    public static WindowManager.LayoutParams getParam() {
        final WindowManager.LayoutParams param;

        int layout_parms;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layout_parms = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        } else {
            layout_parms = WindowManager.LayoutParams.TYPE_PHONE;
//            layout_parms = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }


        param = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                layout_parms,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER,
                PixelFormat.TRANSLUCENT);

          return param;
    }

    public synchronized static View getView(Context context) {
        Log.d("thisView", "getView: " + thisView);

        if (thisView == null) {
            UserView view = new UserView(context);
            thisView = view;
        }
        SharedPreferences preferences = thisView.getContext().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        try {
            thisView.setBackgroundDrawable(BackgroundUtility
                    .getBitmapDrawableFromAsset(
                            thisView.getContext(),
                            preferences.getString("background",
                                    "background/" + thisView.getContext().getAssets().list("background")[3])));
        } catch (IOException e) {
            e.printStackTrace();
//			Tracker tracker = EasyTracker.getInstance(context);
//			tracker.send(MapBuilder.createException(
//					new AnalyticsExceptionParser().getDescription(Thread
//							.currentThread().toString(), e), false).build());
        }
        return thisView;
    }

}