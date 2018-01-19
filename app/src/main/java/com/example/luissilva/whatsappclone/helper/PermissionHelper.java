package com.example.luissilva.whatsappclone.helper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luissilva on 18/01/18.
 */

public class PermissionHelper {

    static private String[] permissions = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    public static boolean validatePermission(Activity activity, int requestCode){

        List<String> permissionsList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= 23){
            for (String permission : permissions){
                Boolean permissionValid = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
                if (!permissionValid){
                    permissionsList.add(permission);
                }
            }

            if (permissionsList.isEmpty()){
                return true;
            }

            String[] newPermissions = new String[permissionsList.size()];
            permissionsList.toArray(newPermissions);

            ActivityCompat.requestPermissions(activity,newPermissions,requestCode);
        }

        return true;
    }

}
