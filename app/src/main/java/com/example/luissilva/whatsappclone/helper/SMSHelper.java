package com.example.luissilva.whatsappclone.helper;

import android.content.Context;
import android.telephony.SmsManager;

/**
 * Created by luishsilva on 18/01/18.
 */

public class SMSHelper {

    private Context mContext;
    private static String phoneNumber;
    private static String mensage;

    public SMSHelper(Context pContext,String pPhoneNumber, String pMessage) {
        this.phoneNumber = "+"+pPhoneNumber;
        this.mensage = pMessage;
        this.mContext = pContext;
    }

    public static boolean sendSMS(){
        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null, mensage,null,null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
