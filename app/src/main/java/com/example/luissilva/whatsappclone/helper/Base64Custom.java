package com.example.luissilva.whatsappclone.helper;


import android.util.Base64;

/**
 * Created by luissilva on 23/01/18.
 */

public class Base64Custom {

    public static String encode(String string){
        return Base64.encodeToString(string.getBytes(),Base64.DEFAULT).replace("\\n","\\r");
    }

    public static String decode(String string){
        return new String(Base64.decode(string,Base64.DEFAULT));
    }

}
