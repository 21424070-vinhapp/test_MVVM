package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    dataviewmodel mDataviewmodel;
    List<Contacts> lstContact;
    ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //create cursor
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        //create dataviewmodel
        mDataviewmodel = new ViewModelProvider(this).get(dataviewmodel.class);

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }
        addview();
        mDataviewmodel.setContacts(cursor);
        //ajfbsakjdkasvf
    }

    private void addview() {
        mDataviewmodel.getContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                lstContact = contacts;
                mAdapter = new ContactAdapter(lstContact);
                mBinding.rcvBackupContact.setAdapter(mAdapter);
            }
        });
    }
}