package com.example.luissilva.whatsappclone.utils;

import java.util.Random;

/**
 * Created by luissilva on 18/01/18.
 */

public class NumberUtils {

    public static int randomNumber(){
        Random random = new Random();
        return random.nextInt(9999 - 1000) + 1000;
    }

}
