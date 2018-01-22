package com.example.luissilva.whatsappclone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.adapter.TabAdapter;
import com.example.luissilva.whatsappclone.dataBaseConfig.DataBaseConfig;
import com.example.luissilva.whatsappclone.helper.SlidingTabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    private DatabaseReference referenceDataBase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolbar);
        toolBar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolBar);

        slidingTabLayout = findViewById(R.id.slidingTabLayout);
        viewPager        = findViewById(R.id.viewPager);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sign_out :{
                DataBaseConfig.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
