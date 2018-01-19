package com.example.luissilva.whatsappclone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.luissilva.whatsappclone.R;

public class LoginActivity extends AppCompatActivity {

    private TextView tvNoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnRegister = findViewById(R.id.btnLogin);
        TextView tvNoRegister = findViewById(R.id.tvNoRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvNoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,UserRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
