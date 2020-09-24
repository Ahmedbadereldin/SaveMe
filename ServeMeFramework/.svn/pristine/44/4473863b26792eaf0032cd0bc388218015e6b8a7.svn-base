package com.serveme.framework.app;

import android.app.Application;
import android.content.Context;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.ExceptionReporter;
import com.serveme.framework.analytics.AnalyticsExceptionParser;

public class AnalyticsApplication extends Application {
    public static void changeExceptionParser(Context context) {
        EasyTracker.getInstance(context);

        // Change uncaught exception parser...
        // Note: Checking uncaughtExceptionHandler type can be useful if
        // clearing ga_trackingId during development to disable analytics -
        // avoid NullPointerException.
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread
                .getDefaultUncaughtExceptionHandler();
        if (uncaughtExceptionHandler instanceof ExceptionReporter) {
            ExceptionReporter exceptionReporter = (ExceptionReporter) uncaughtExceptionHandler;
            exceptionReporter
                    .setExceptionParser(new AnalyticsExceptionParser());
        }

    }

    @Override
    public void onCreate() {
        changeExceptionParser(this);
        super.onCreate();
    }
}
