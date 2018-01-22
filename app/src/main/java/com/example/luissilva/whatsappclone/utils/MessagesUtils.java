package com.example.luissilva.whatsappclone.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by luissilva on 21/01/18.
 */

public class MessagesUtils {

    public MessagesUtils() {
    }

    public static void toastMsg(Context pContext, String msg){
        Toast.makeText(pContext, msg, Toast.LENGTH_SHORT).show();
    }

}
