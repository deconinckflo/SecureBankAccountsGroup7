package com.group7.secureBankAccounts.data.model;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.group7.secureBankAccounts.R;

public class AllAccountViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView amount;
    TextView iban;

    public AllAccountViewHolder(@NonNull View itemView) {

        super(itemView);
        this.name = itemView.findViewById(R.id.accountNameFor);
        this.iban = itemView.findViewById(R.id.ibanFor);
        this.amount = itemView.findViewById(R.id.amountFor);
    }

    public void update(Context c, BankAccount bank){
        this.name.setText(bank.getAccount_name());
        this.amount.setText(String.valueOf(bank.getAmount()) + " " + bank.getCurrency());
        if(bank.getAmount() >=0){
            amount.setTextColor(ContextCompat.getColor(c,R.color.green));
        }
        else{
            amount.setTextColor(ContextCompat.getColor(c,R.color.red));
        }
        this.iban.setText(bank.getIban());
    }
}
