package com.example.luissilva.whatsappclone.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.helper.PreferencesHelper;
import com.example.luissilva.whatsappclone.utils.StringUtils;

import java.util.HashMap;

public class ValidatorActivity extends AppCompatActivity {

    private Context context;

    private EditText numberCode;
    private Button validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        context = getBaseContext();

        numberCode = findViewById(R.id.edtCode);
        validate        = findViewById(R.id.btnValidate);

        StringUtils.editTextFormater(numberCode,"NNNN");

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get token on preferences
                PreferencesHelper preferencesHelper = new PreferencesHelper(getBaseContext());
                HashMap<String, String> userData = preferencesHelper.getUserData();

                String tokenPreferences = userData.get("token");
                String tokenTyped = numberCode.getText().toString();

                if (tokenTyped.equals(tokenPreferences)){
                    StringUtils.ToastLong(context,getString(R.string.token_valid));
                }else{
                    StringUtils.ToastLong(context,getString(R.string.token_not_valid));
                }
            }
        });

    }
}
