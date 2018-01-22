package com.example.luissilva.whatsappclone.dataBaseConfig;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by luissilva on 21/01/18.
 */

public class DataBaseConfig {

    private static DatabaseReference databaseReference;
    private static FirebaseAuth authentication;

    public static DatabaseReference getDataBaseReference(){
        if (databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }

    public static FirebaseAuth getAuthentication(){
        if (authentication == null){
            authentication = FirebaseAuth.getInstance();
        }else{

        }
        return authentication;
    }

    public static void signOut(){
        authentication = FirebaseAuth.getInstance();
        authentication.signOut();
    }

}
