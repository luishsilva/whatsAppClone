package com.example.luissilva.whatsappclone.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by luishsilva on 18/01/18.
 */

public class Preferences {

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedEditor;
    private int MODE = 0;

    // Constants
    private String SHARED_PREFERENCES_FILE_NAME = "whatsapp.preferences";
    private String KEY_USER_NAME = "name";
    private String KEY_USER_PHONE = "phone";
    private String KEY_USER_TOKEN = "token";

    public Preferences(Context pContext) {
        this.mContext = pContext;
        this.mSharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME,MODE);
        mSharedEditor = mSharedPreferences.edit();

    }

    public void saveUserPreferences(String pName, String pPhoneNUmber, String pToken){
        mSharedEditor.putString(KEY_USER_NAME,pName);
        mSharedEditor.putString(KEY_USER_PHONE,pPhoneNUmber);
        mSharedEditor.putString(KEY_USER_TOKEN,pToken);
        mSharedEditor.commit();
    }

    public HashMap<String,String> getUserData(){
        HashMap<String, String> userData = new HashMap<>();
        userData.put(KEY_USER_NAME, mSharedPreferences.getString(KEY_USER_NAME,null));
        userData.put(KEY_USER_PHONE, mSharedPreferences.getString(KEY_USER_PHONE,null));
        userData.put(KEY_USER_TOKEN, mSharedPreferences.getString(KEY_USER_TOKEN,null));
        return userData;
    }
}
