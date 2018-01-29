package com.example.luissilva.whatsappclone.presenter;

import com.example.luissilva.whatsappclone.dataBaseConfig.DataBaseConfig;
import com.example.luissilva.whatsappclone.helper.Base64Custom;
import com.example.luissilva.whatsappclone.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by luissilva on 21/01/18.
 */

public class PresenterUser {

    private static FirebaseAuth mAuth;
    private static String userId;

    public PresenterUser() {
    }

    public static Task<AuthResult> registerUserAuth(final User pUser){

        mAuth = DataBaseConfig.getAuthentication();
        return mAuth.createUserWithEmailAndPassword(pUser.getEmail(),
                                             pUser.getPass());
    }

    public static Task<Void> registerUser(User pUser){
        DatabaseReference databaseReference = DataBaseConfig.getDataBaseReference();

        userId = Base64Custom.encode(pUser.getEmail());

        return databaseReference.child("users").child(userId).setValue(pUser);
        //return databaseReference.child("user").child(pUser.getId()).setValue(pUser);
    }

    public static Task<AuthResult> validateUserAuth(User pUser){
        mAuth = DataBaseConfig.getAuthentication();
        return mAuth.signInWithEmailAndPassword(pUser.getEmail(), pUser.getPass());
    }
}
