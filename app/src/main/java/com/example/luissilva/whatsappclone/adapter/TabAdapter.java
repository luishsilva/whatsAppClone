package com.example.luissilva.whatsappclone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.luissilva.whatsappclone.fragment.ContactsFragment;
import com.example.luissilva.whatsappclone.fragment.TalksFragment;

/**
 * Created by luishsilva on 22/01/18.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    String[] titlePages = {"TALKS","CONTACTS"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0 :
            {
                fragment = new TalksFragment();
                break;
            }
            case 1 : {
                fragment = new ContactsFragment();
                break;
            }
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return titlePages.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlePages[position];
    }
}
