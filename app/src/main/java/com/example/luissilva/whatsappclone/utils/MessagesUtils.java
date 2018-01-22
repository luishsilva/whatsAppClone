package com.example.luissilva.whatsappclone.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by luissilva on 21/01/18.
 */

public class MessagesUtils {

    private static Context mContext;

    public MessagesUtils(Context pContext) {
        this.mContext = pContext;
    }

    public static void toastMsg(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
