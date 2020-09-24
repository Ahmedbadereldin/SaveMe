package com.serveme.framework.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.serveme.framework.R;

public class PackageUtil {
    public static boolean isFree(Context context) {
        return context.getPackageName().indexOf("free") != -1;
    }

    public static void rateMyApp(Context context) {
        String packageName = context.getPackageName();
        String installerPackageName = context.getPackageManager()
                .getInstallerPackageName(packageName);
        Uri uri = null;
        if (installerPackageName != null) {
            if (installerPackageName.contains("samsung")) {
                uri = Uri.parse("market://details?id=" + packageName);
            } else if (installerPackageName.contains("google")) {
                uri = Uri.parse("samsungapps://ProductDetail/" + packageName);
            } else {
                com.google.analytics.tracking.android.Log
                        .v(installerPackageName);
            }
        }

        if (uri != null) {
            final Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, uri);
            rateAppIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
                rateAppIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            }
            context.startActivity(rateAppIntent);
            EasyTracker.getInstance(context).send(
                    MapBuilder.createEvent("ui_action", "button_press",
                            "rateme_button", Long.valueOf(1)).build());
        } else {
            Toast.makeText(
                    context,
                    context.getResources()
                            .getString(R.string.unresolved_market),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static void openMyApp(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);

        final Intent openAppIntent = new Intent(Intent.ACTION_VIEW, uri);
        openAppIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            openAppIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        }
        try {
            context.startActivity(openAppIntent);
            EasyTracker.getInstance(context).send(
                    MapBuilder.createEvent("ui_action", "button_press",
                            "open_app_button", Long.valueOf(1)).build());
        } catch (Exception exception) {
            Toast.makeText(context,
                    context.getResources().getString(R.string.unexist_market),
                    Toast.LENGTH_SHORT).show();
        }

    }
}
