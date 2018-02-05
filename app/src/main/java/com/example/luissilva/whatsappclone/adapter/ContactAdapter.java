package com.example.luissilva.whatsappclone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.model.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by luissilva on 02/02/18.
 */

public class ContactAdapter extends ArrayAdapter<Contact>{

    private Context mContext;
    private ArrayList<Contact> mContactArrayList;
    private TextView nome;
    private TextView email;

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
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell_item_contacts, parent, false);

            nome  = view.findViewById(R.id.tvNome);
            email = view.findViewById(R.id.tvEmail);

            Contact contact = mContactArrayList.get(position);

            nome.setText(contact.getNome());
            email.setText(contact.getEmail());
        }
        return view;
    }

}
