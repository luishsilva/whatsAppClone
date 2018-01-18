package com.example.luissilva.whatsappclone.utils;

import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

/**
 * Created by luissilva on 18/01/18.
 */

public class StringUtils {

    public static void editTextFormater(EditText editText, String format){
        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter(format);
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(editText, simpleMaskFormatter);

        editText.addTextChangedListener(maskTextWatcher);
    }

}
