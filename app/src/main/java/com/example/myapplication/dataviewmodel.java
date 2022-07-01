package com.example.myapplication;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class dataviewmodel extends ViewModel {
    private MutableLiveData<List<Contacts>> lstContacts=new MutableLiveData<>();


    public LiveData<List<Contacts>> getContacts() {
        return lstContacts;
    }

    public void setContacts(Cursor cursor) {
        List<Contacts> current;
        current=new ArrayList<>();
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String idName=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
                int colNameIndex=cursor.getColumnIndex(idName);
                String name="Name: "+cursor.getString(colNameIndex);

                String idPhone=ContactsContract.CommonDataKinds.Phone.NUMBER;
                int colPhoneIndex=cursor.getColumnIndex(idPhone);
                String phone="Phone: "+cursor.getString(colPhoneIndex);
                //Log.d("bbb", "getAllContacFromDevice: "+ name + " "+ phone);
                current.add(new Contacts(name,phone));
            }
        }
        cursor.close();
        lstContacts.setValue(current);
    }
}
