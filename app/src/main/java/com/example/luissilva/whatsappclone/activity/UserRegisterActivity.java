package com.example.luissilva.whatsappclone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.model.User;
import com.example.luissilva.whatsappclone.presenter.PresenterUser;
import com.example.luissilva.whatsappclone.utils.MessagesUtils;

public class UserRegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText pass;
    private Button btnRegister;

    private User user;
    private PresenterUser presenterUser;
    private MessagesUtils messagesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        name = findViewById(R.id.edtName);
        email    = findViewById(R.id.edtEmail);
        pass     = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setName(name.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPass(pass.getText().toString());

                presenterUser = new PresenterUser(UserRegisterActivity.this);
                if(presenterUser.registerUserAuth(user)){
                    messagesUtils = new MessagesUtils(UserRegisterActivity.this);
                    messagesUtils.toastMsg(getString(R.string.registration_success));
                }

            }
        });
    }
}
