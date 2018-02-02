package com.example.luissilva.whatsappclone.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.adapter.TabAdapter;
import com.example.luissilva.whatsappclone.dataBaseConfig.DataBaseConfig;
import com.example.luissilva.whatsappclone.helper.Base64Custom;
import com.example.luissilva.whatsappclone.helper.PreferencesHelper;
import com.example.luissilva.whatsappclone.helper.SlidingTabLayout;
import com.example.luissilva.whatsappclone.model.Contact;
import com.example.luissilva.whatsappclone.model.User;
import com.example.luissilva.whatsappclone.utils.Constants;
import com.example.luissilva.whatsappclone.utils.MessagesUtils;
import com.example.luissilva.whatsappclone.utils.StringUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    String userIdContact;
    private DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolbar);
        toolBar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolBar);

        slidingTabLayout = findViewById(R.id.slidingTabLayout);
        viewPager = findViewById(R.id.viewPager);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sign_out:{
                DataBaseConfig.signOut();
                if (!DataBaseConfig.checkUserAuthStatus()){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            }

            case R.id.action_add:{
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(StringUtils.getStringResourceId(this, R.string.add_person));
                alertDialog.setMessage(StringUtils.getStringResourceId(this, R.string.email_user));
                alertDialog.setCancelable(false);

                final EditText editText = new EditText(MainActivity.this);
                editText.setPadding(10,10,10,10);
                alertDialog.setView(editText);

                alertDialog.setPositiveButton(StringUtils.getStringResourceId(this, R.string.txtRegister), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String contactEmail = editText.getText().toString();
                        if (contactEmail.isEmpty()){
                            MessagesUtils.toastMsg(getBaseContext(),"Para fazer o cadastro preencha o Email.");
                        }else{
                            userIdContact = Base64Custom.encode(contactEmail);

                            firebase = DataBaseConfig.getDataBaseReference().child(Constants.USERS_FIREBASE_NODE).child(userIdContact);
                            firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    User user = dataSnapshot.getValue(User.class);

                                    if (dataSnapshot.getValue() != null){

                                        PreferencesHelper preferencesHelper = new PreferencesHelper(MainActivity.this);

                                        firebase = DataBaseConfig.getDataBaseReference();
                                        firebase =  firebase.child(Constants.CONTACTS_FIREBASE_NODE)
                                                            .child(preferencesHelper.getUserLogged())
                                                            .child(userIdContact);

                                        Contact contact = new Contact();
                                        contact.setUserIdentifier(userIdContact);
                                        contact.setNome(user.getName());
                                        contact.setEmail(user.getEmail());

                                        firebase.setValue(contact);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            MessagesUtils.toastMsg(getBaseContext(),userIdContact);
                        }
                    }
                });

                alertDialog.setNegativeButton(StringUtils.getStringResourceId(this, R.string.txtCancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_sign_out: {
                DataBaseConfig.signOut();
                if (!DataBaseConfig.checkUserAuthStatus()) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }*/
}
