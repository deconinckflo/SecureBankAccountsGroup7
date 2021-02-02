package com.group7.secureBankAccounts.data.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group7.secureBankAccounts.R;

public class AllAccountViewHolder extends RecyclerView.ViewHolder {

    TextView t;

    public AllAccountViewHolder(@NonNull View itemView) {

        super(itemView);
        this.t = itemView.findViewById(R.id.fragment_main_item_title);
    }

    public void update(BankAccount prout){
        this.t.setText(prout.getAccount_name());
    }
}
