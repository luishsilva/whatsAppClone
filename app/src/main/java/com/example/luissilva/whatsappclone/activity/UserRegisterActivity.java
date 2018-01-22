package com.example.luissilva.whatsappclone.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.model.User;
import com.example.luissilva.whatsappclone.presenter.PresenterUser;
import com.example.luissilva.whatsappclone.utils.MessagesUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText pass;
    private Button btnRegister;

    private User mUser;
    private PresenterUser presenterUser;
    private MessagesUtils messagesUtils;
    private FirebaseAuth mAuth;

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
                mUser = new User();
                mUser.setName(name.getText().toString());
                mUser.setEmail(email.getText().toString());
                mUser.setPass(pass.getText().toString());

                presenterUser = new PresenterUser();
                // register the user on Authetication
                presenterUser.registerUserAuth(mUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // get user ID at Firebase
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            mUser.setId(firebaseUser.getUid());

                            // register user on database
                            presenterUser.registerUser(mUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    finish();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
