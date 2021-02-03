package com.group7.secureBankAccounts.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group7.secureBankAccounts.R;
import com.group7.secureBankAccounts.data.model.AllAccountAdapter;
import com.group7.secureBankAccounts.data.model.BankAccount;
import com.group7.secureBankAccounts.data.model.Users;
import com.group7.secureBankAccounts.ui.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllAccountFragment extends Fragment {

    private RecyclerView recyclerView;


    private AllAccountAdapter adapter;

    private Users user;



    public void refresh(Users a){
        user =a;
        adapter.setAllBankAccount(user.getAllBankAccount());
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_all_account, container, false);



        Bundle args = getArguments();
        user = (Users) args.getSerializable("User");
        recyclerView = v.findViewById(R.id.recycler_view_all_account);
        this.adapter = new AllAccountAdapter(user.getAllBankAccount());
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }
}
