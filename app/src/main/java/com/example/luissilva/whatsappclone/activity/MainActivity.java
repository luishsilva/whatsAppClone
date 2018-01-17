package com.example.luissilva.whatsappclone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luissilva.whatsappclone.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referenceDataBase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         referenceDataBase.child("pontos").setValue(100);
    }
}
