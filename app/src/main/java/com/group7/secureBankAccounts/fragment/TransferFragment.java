package com.group7.secureBankAccounts.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.group7.secureBankAccounts.R;
import com.group7.secureBankAccounts.data.model.BankAccount;
import com.group7.secureBankAccounts.data.model.Users;

import java.util.ArrayList;
import java.util.List;

public class TransferFragment extends Fragment {

    private Spinner spinnerBankSince;
    private Spinner spinnerBankFor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transfer, container, false);

        Bundle args = getArguments();
        Users user = (Users) args.getSerializable("User");
        
        spinnerBankSince = v.findViewById(R.id.spinnerBankSince);
        spinnerBankFor = v.findViewById(R.id.spinnerBankFor);

        List<String> items = new ArrayList<>();
        for (BankAccount bank : user.getAllBankAccount()){
            items.add(bank.getAccount_name());
        }
        
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);

        spinnerBankSince.setAdapter(adapter);
        spinnerBankFor.setAdapter(adapter);
        return v;
    }
}
