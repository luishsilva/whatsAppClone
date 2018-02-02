package com.example.luissilva.whatsappclone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.luissilva.whatsappclone.R;
import com.example.luissilva.whatsappclone.adapter.ContactAdapter;
import com.example.luissilva.whatsappclone.dataBaseConfig.DataBaseConfig;
import com.example.luissilva.whatsappclone.helper.PreferencesHelper;
import com.example.luissilva.whatsappclone.model.Contact;
import com.example.luissilva.whatsappclone.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ContactsFragment extends Fragment {

    private ArrayList<Contact> contacts;
    private ListView listViewConcat;
    private ArrayAdapter<Contact> adapter;

    DatabaseReference databaseReference;

    ValueEventListener valueEventListener;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        // add listener only when the fragment start
        databaseReference.addValueEventListener(valueEventListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        // remove listener
        databaseReference.removeEventListener(valueEventListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contacts = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        listViewConcat = view.findViewById(R.id.list_contacts);
        /*adapter = new ArrayAdapter(getActivity(),
                R.layout.cell_item_contacts,
                contacts);*/
        adapter = new ContactAdapter(getActivity(),contacts);
        listViewConcat.setAdapter(adapter);

        PreferencesHelper preferencesHelper = new PreferencesHelper(getActivity());

        databaseReference = DataBaseConfig.getDataBaseReference()
                .child(Constants.CONTACTS_FIREBASE_NODE)
                .child(preferencesHelper.getUserLogged());

        databaseReference.orderByValue();

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // clear list
                contacts.clear();

                for (DataSnapshot dataUser : dataSnapshot.getChildren()){
                    Contact contact = dataUser.getValue(Contact.class);
                    contacts.add(contact);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        return  view;
    }
}
