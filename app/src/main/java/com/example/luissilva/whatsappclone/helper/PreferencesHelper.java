package com.example.luissilva.whatsappclone.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by luishsilva on 18/01/18.
 */

public class PreferencesHelper {

    private Context mContext;
    private String SHARED_PREFERENCES_FILE_NAME = "whatsAppClone.preferences";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedEditor;
    private int MODE = 0;

    // Constants
    private String KEY_USERID_DATABASE = "idUserLogged";

    public PreferencesHelper(Context pContext) {
        this.mContext = pContext;
        this.mSharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME,MODE);
        mSharedEditor = mSharedPreferences.edit();

    }

    public void saveUserPreferences(String pUserIdLogged){
        mSharedEditor.putString(KEY_USERID_DATABASE,pUserIdLogged);
        mSharedEditor.commit();
    }

    public String getUserLogged(){
        return mSharedPreferences.getString(KEY_USERID_DATABASE,null);
    }


}
