package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.databinding.ItemContactBinding;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewholder> {

    private List<Contacts> lstContact;

    public ContactAdapter(List<Contacts> lstContact) {
        if(this.lstContact!=null && this.lstContact.size()>0){
            this.lstContact.clear();
        }
        this.lstContact = lstContact;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        ItemContactBinding binding=ItemContactBinding.inflate(layoutInflater,parent,false);
        return new ContactViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewholder holder, int position) {
        holder.bind(lstContact.get(position));
    }

    @Override
    public int getItemCount() {
        if(lstContact!=null)
        {
            return lstContact.size();
        }
        return 0;
    }

    public class ContactViewholder extends RecyclerView.ViewHolder {
        ItemContactBinding mBinding;
        public ContactViewholder(@NonNull ItemContactBinding itemView) {
            super(itemView.getRoot());

            mBinding=itemView;


        }
        void bind(Contacts contacts)
        {
            mBinding.setContact(contacts);
        }
    }

}