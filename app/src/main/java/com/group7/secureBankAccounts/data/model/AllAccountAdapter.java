package com.group7.secureBankAccounts.data.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group7.secureBankAccounts.R;

import java.util.List;

public class AllAccountAdapter extends RecyclerView.Adapter<AllAccountViewHolder>{


    private List<BankAccount> allBankAccount;
    private Context c;

    public AllAccountAdapter(List<BankAccount> allBankAccount) {
        this.allBankAccount = allBankAccount;
    }

    @NonNull
    @Override
    public AllAccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.fragment_recycler_view_all_account, parent, false);
        return new AllAccountViewHolder(view);
    }


    public void setAllBankAccount(List<BankAccount> allBankAccount){
        this.allBankAccount = allBankAccount;

    }

    @Override
    public void onBindViewHolder(@NonNull AllAccountViewHolder holder, int position) {
        holder.update(c,this.allBankAccount.get(position));
    }

    @Override
    public int getItemCount() {
        return this.allBankAccount.size();
    }
}
