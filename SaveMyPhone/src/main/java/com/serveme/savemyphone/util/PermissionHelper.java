package com.serveme.savemyphone.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

public class PermissionHelper {


    // to handel permissiom Code

    public static final int CODE_PERMISSION_READ_PHONE_STATE = 11;
    public static final int CODE_PERMISSION_CAMERA =22;
    public static final int CODE_PERMISSION_READ_CONTACTS = 33;
    public static final int CODE_PERMISSION_READ_SMS = 44;
    public static final int CODE_PERMISSION_READ_CALL_LOG = 55;
    public final static int CODE_OVERLAY_PERMISSION = 66;
    public final static int CODE_UsageStats_PERMISSION = 77;


    // to handel permissiom v6
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    public static final String PERMISSION_WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    public static final String PERMISSION_READ_SMS = Manifest.permission.READ_SMS;
    public static final String PERMISSION_READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;

    public static final String[] PERMISSIONS_LIST = {
            PERMISSION_READ_PHONE_STATE,
            PERMISSION_CAMERA,
            PERMISSION_READ_CONTACTS,
            PERMISSION_WRITE_CONTACTS,
            PERMISSION_READ_CALL_LOG
    };
    public static final String[] PERMISSIONS_LIST_PAERNT = {
            PERMISSION_READ_PHONE_STATE
    };

    public static boolean checkDrawOverlayPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context))
            return false;
        else return true;
    }
    public static void drawOverlayPermission(Context context, int REQUEST_CODE) {
        /** check if we already  have permission to draw over other apps */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + context.getPackageName()));
            /** request permission via start activity for result */
            ((Activity) context).startActivityForResult(intent, REQUEST_CODE);
        }
    }
}
