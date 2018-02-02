package com.example.luissilva.whatsappclone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.luissilva.whatsappclone.model.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by luissilva on 02/02/18.
 */

public class ContactAdapter extends ArrayAdapter<Contact>{

    private Context mContext;
    private ArrayList<Contact> mContactArrayList;

    public ContactAdapter(Context  pContext, ArrayList<Contact> pContactArrayList) {
        super(pContext,0,pContactArrayList);
        this.mContactArrayList = pContactArrayList;
        this.mContext = pContext;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if (mContactArrayList != null){
            //LayoutInflater inflater = mContext.getSystemService();
        }

        return super.getView(position, convertView, parent);
    }

}
