package com.example.luissilva.whatsappclone.helper;

import android.telephony.SmsManager;

/**
 * Created by luishsilva on 18/01/18.
 */

public class SMSHelper {

    private static String phoneNumber;
    private static String mensage;

    public SMSHelper(String pPhoneNumber, String pMessage) {
        this.phoneNumber = pPhoneNumber;
        this.mensage = pMessage;
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
