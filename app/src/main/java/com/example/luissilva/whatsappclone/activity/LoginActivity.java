package com.example.luissilva.whatsappclone.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.helper.PermissionHelper;
import com.example.luissilva.whatsappclone.helper.PreferencesHelper;
import com.example.luissilva.whatsappclone.helper.SMSHelper;
import com.example.luissilva.whatsappclone.utils.NumberUtils;
import com.example.luissilva.whatsappclone.utils.StringUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // validate android permission
        PermissionHelper.validatePermission(this,1);

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
                SMSHelper smsHelper = new SMSHelper(getApplicationContext(),phoneWithOutString,getString(R.string.sms_message)+" "+token);
                boolean returnSendSMS = smsHelper.sendSMS();

                if (returnSendSMS){
                    Intent intent = new Intent(LoginActivity.this, ValidatorActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, StringUtils.getStringResourceId(getBaseContext(),R.string.sms_not_sended) , Toast.LENGTH_SHORT).show();
                }

                //HashMap<String,String> userData = preferences.getUserData();

                //Log.d("TOKEN",userData.get("name")+" - "+userData.get("phone")+" - "+userData.get("token"));

            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults ){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults){
            if (result == PackageManager.PERMISSION_DENIED){
                alertPermissaoDenied();
            }
        }
    }

    private void alertPermissaoDenied() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.title_alert_dialog_permission));
        builder.setMessage(getString(R.string.msg_alert_dialog_permission));

        builder.setPositiveButton(getString(R.string.btn_confirm_dialog_permission), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

    }
}
