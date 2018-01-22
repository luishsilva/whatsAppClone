package com.example.luissilva.whatsappclone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.model.User;
import com.example.luissilva.whatsappclone.presenter.PresenterUser;
import com.example.luissilva.whatsappclone.utils.MessagesUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private EditText edtEmail;
    private EditText edtPassWord;

    private TextView tvNoRegister;
    private Button btnRegister;
    private String mString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail    = findViewById(R.id.edtEmail);
        edtPassWord = findViewById(R.id.edtPassWord);

        btnRegister = findViewById(R.id.btnLogin);
        tvNoRegister = findViewById(R.id.tvNoRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PresenterUser presenterUser = new PresenterUser();
                User mUser = new User();
                mUser.setEmail(edtEmail.getText().toString());
                mUser.setPass(edtPassWord.getText().toString());
                presenterUser.validateUserAuth(mUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthInvalidUserException e){
                                mString = "O email informado ainda não possue cadastro no nosso sistema";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                mString = "O email ou senha informado não é válido";
                            }catch (Exception e) {
                                mString = "Erro ao logar usuário";e.printStackTrace();
                            }
                            MessagesUtils.toastMsg(getBaseContext(),mString);
                        }
                    }
                });
            }
        });

        tvNoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,UserRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
