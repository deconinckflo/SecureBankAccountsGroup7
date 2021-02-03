package com.group7.secureBankAccounts.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.group7.secureBankAccounts.R;
import com.group7.secureBankAccounts.data.model.BankAccount;
import com.group7.secureBankAccounts.data.model.SpinnerAdapter;
import com.group7.secureBankAccounts.data.model.Users;
import com.group7.secureBankAccounts.ui.home.HomeActivity;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.math.BigDecimal;
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

    private int posBankAccountFor;
    private int posBankAccountFrom;

    private List<BankAccount> items;

    private EditText editTextNumberAmount;

    public void setAllTextViewFor(BankAccount bank) {
        nameFor.setText(bank.getAccount_name());
        amountFor.setText(String.valueOf(bank.getAmount()) + " " + bank.getCurrency());

        if (bank.getAmount().compareTo(BigDecimal.ZERO)>= 0) {

            amountFor.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
        } else {
            amountFor.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }


        ibanFor.setText(bank.getIban());

    }

    public void setAllTextViewFrom(BankAccount bank) {
        nameFrom.setText(bank.getAccount_name());
        amountFrom.setText(String.valueOf(bank.getAmount()) + " " + bank.getCurrency());

        if (bank.getAmount().compareTo(BigDecimal.ZERO)>= 0) {

            amountFrom.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
        } else {
            amountFrom.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }

        ibanFrom.setText(bank.getIban());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transfer, container, false);

        Bundle args = getArguments();
        Users user = (Users) args.getSerializable("User");

        spinnerBankFrom = v.findViewById(R.id.spinnerBankFrom);
        spinnerBankFor = v.findViewById(R.id.spinnerBankFor);

        items = user.getAllBankAccount();
        editTextNumberAmount = (EditText) v.findViewById(R.id.editTextNumberAmount);
        this.buttonTransfer = v.findViewById(R.id.buttonTransfer);


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
                setAllTextViewFrom(bank);
                posBankAccountFrom = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        spinnerBankFor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                BankAccount bank = adapter.getItem(position);
                setAllTextViewFor(bank);
                posBankAccountFor = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        buttonTransfer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                Double amount = Double.parseDouble(String.valueOf(editTextNumberAmount.getText()));


                if (String.valueOf(editTextNumberAmount.getText()).isEmpty()) {
                    editTextNumberAmount.setError("Veuillez préciser un montant");
                    DynamicToast.makeError(getContext(), "Veuillez préciser un montant", 1000).show();
                } else if (user.getAllBankAccount().get(posBankAccountFor).get_id() == user.getAllBankAccount().get(posBankAccountFrom).get_id()) {
                    DynamicToast.makeError(getContext(), "Impossible de faire un virement sur le même compte", 1000).show();
                } else if (user.getAllBankAccount().get(posBankAccountFrom).getAmount().compareTo(BigDecimal.valueOf(amount)) < 0 ) {
                    DynamicToast.makeError(getContext(), "Ressource insuffisante", 1000).show();
                } else {

                    HomeActivity parent = (HomeActivity) getActivity();

                    List<BankAccount> allBankAccount = user.getAllBankAccount();

                    allBankAccount.get(posBankAccountFor).setAmount(allBankAccount.get(posBankAccountFor).getAmount().add(BigDecimal.valueOf(amount)) );
                    allBankAccount.get(posBankAccountFrom).setAmount(allBankAccount.get(posBankAccountFrom).getAmount().subtract(BigDecimal.valueOf(amount)));

                    user.setAllBankAccount(allBankAccount);

                    parent.setUser(user);
                    items = allBankAccount;
                    adapter.notifyDataSetChanged();
                    setAllTextViewFor(allBankAccount.get(posBankAccountFor));
                    setAllTextViewFrom(allBankAccount.get(posBankAccountFrom));
                    DynamicToast.makeSuccess(getContext(), "Virement effectué", 1000).show();
                }
            }
        });


        return v;
    }


}
