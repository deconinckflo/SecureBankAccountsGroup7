package com.group7.secureBankAccounts.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.group7.secureBankAccounts.R;
import com.group7.secureBankAccounts.data.model.BankAccount;
import com.group7.secureBankAccounts.data.model.SpinnerAdapter;
import com.group7.secureBankAccounts.data.model.Users;

import java.util.List;

public class TransferFragment extends Fragment {

    private Spinner spinnerBankFrom;
    private Spinner spinnerBankFor;
    private SpinnerAdapter adapter;


    private TextView nameFor;
    private TextView ibanFor;
    private TextView amountFor;

    private TextView nameFrom;
    private TextView ibanFrom;
    private TextView amountFrom;

    private Button buttonTransfer;

    private BankAccount bankAccountFor;
    private BankAccount bankAccountFrom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transfer, container, false);

        Bundle args = getArguments();
        Users user = (Users) args.getSerializable("User");
        
        spinnerBankFrom = v.findViewById(R.id.spinnerBankFrom);
        spinnerBankFor = v.findViewById(R.id.spinnerBankFor);

        List<BankAccount> items = user.getAllBankAccount();

        this.buttonTransfer = v.findViewById(R.id.buttonTransfer);
        buttonTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        adapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, items);


        this.nameFor = v.findViewById(R.id.accountNameFor);
        this.ibanFor = v.findViewById(R.id.ibanFor);
        this.amountFor = v.findViewById(R.id.amountFor);

        this.nameFrom = v.findViewById(R.id.accountNameFrom);
        this.ibanFrom = v.findViewById(R.id.ibanFrom);
        this.amountFrom = v.findViewById(R.id.amountFrom);


        spinnerBankFrom.setAdapter(adapter);
        spinnerBankFor.setAdapter(adapter);


        spinnerBankFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                BankAccount bank = adapter.getItem(position);
                nameFrom.setText(bank.getAccount_name());
                amountFrom.setText(String.valueOf(bank.getAmount()) + " " + bank.getCurrency());

                if(bank.getAmount() >=0){

                    amountFrom.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
                }
                else{
                    amountFrom.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
                }

                ibanFrom.setText(bank.getIban());
                bankAccountFrom = bank;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        spinnerBankFor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                BankAccount bank = adapter.getItem(position);
                nameFor.setText(bank.getAccount_name());
                amountFor.setText(String.valueOf(bank.getAmount()) + " " + bank.getCurrency());

                if(bank.getAmount() >=0){

                    amountFor.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
                }
                else{
                    amountFor.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
                }


                ibanFor.setText(bank.getIban());
                bankAccountFor = bank;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });


        return v;
    }


}
