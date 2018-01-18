package com.example.luissilva.whatsappclone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.helper.PreferencesHelper;
import com.example.luissilva.whatsappclone.helper.SMSHelper;
import com.example.luissilva.whatsappclone.utils.NumberUtils;
import com.example.luissilva.whatsappclone.utils.StringUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText edtUserName = findViewById(R.id.edtUserName);
        final EditText edtCodCountry = findViewById(R.id.edtCodCountry);
        final EditText edtFoneNumber = findViewById(R.id.edtFoneNumber);
        Button btnRegister = findViewById(R.id.btnRegister);

        StringUtils.editTextFormater(edtCodCountry, "+NN");
        StringUtils.editTextFormater(edtFoneNumber, "(NN)NNNNN-NNNN");


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String phoneWithOutString = edtCodCountry.getText().toString()+""+
                        edtFoneNumber.getText().toString();

                phoneWithOutString = phoneWithOutString.replaceAll("[^a-zZ-Z1-9 ]", "");
                String token =  String.valueOf(NumberUtils.randomNumber());

                PreferencesHelper preferences = new PreferencesHelper(LoginActivity.this);
                preferences.saveUserPreferences(userName,phoneWithOutString,token);

                // Envio do SMS
                SMSHelper smsHelper = new SMSHelper(phoneWithOutString,getString(R.string.sms_message));
                boolean returnSendSMS = smsHelper.sendSMS();

                //HashMap<String,String> userData = preferences.getUserData();

                //Log.d("TOKEN",userData.get("name")+" - "+userData.get("phone")+" - "+userData.get("token"));

            }
        });

    }
}
