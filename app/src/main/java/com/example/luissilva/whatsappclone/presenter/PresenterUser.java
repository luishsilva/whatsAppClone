package com.example.luissilva.whatsappclone.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.luissilva.whatsappclone.dataBaseConfig.DataBaseConfig;
import com.example.luissilva.whatsappclone.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by luissilva on 21/01/18.
 */

public class PresenterUser {

    private static FirebaseAuth mAuth;
    private static Activity mActivity;

    private static boolean mReturn;

    public PresenterUser(Activity pActivity) {
        this.mActivity = pActivity;
    }

    public static boolean registerUserAuth(final User pUser){

        mAuth = DataBaseConfig.getAuthentication();
        mAuth.createUserWithEmailAndPassword(pUser.getEmail(),
                                             pUser.getPass())
        .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mReturn = true;

                    // get user ID at Firebase
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    pUser.setId(firebaseUser.getUid());
                    // save User
                    registerUser(pUser);
                }else{
                    mReturn = false;
                }
            }

        });
        return mReturn;
    }

    public static void registerUser(User pUser){
        DatabaseReference databaseReference = DataBaseConfig.getDataBaseReference();
        databaseReference.child("user").child(pUser.getId()).setValue(pUser);
    }
}
