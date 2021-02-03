package com.group7.secureBankAccounts.ui.home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.group7.secureBankAccounts.ApiRequest.Api;
import com.group7.secureBankAccounts.ApiRequest.ApiInterface;
import com.group7.secureBankAccounts.R;
import com.group7.secureBankAccounts.data.model.BankAccount;
import com.group7.secureBankAccounts.data.model.Users;
import com.group7.secureBankAccounts.fragment.AllAccountFragment;
import com.group7.secureBankAccounts.fragment.TransferFragment;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Fragment fragmentTransfer;
    private Fragment fragmentAllAccount;

    private Users user;




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(false);


    }


    public void initialize(Boolean isRefresh){
        final ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...", true);



        Executor mBackgroundThread = Executors.newSingleThreadExecutor();;
        mBackgroundThread.execute(() -> {

            ApiInterface apiInterface = Api.getClient().create(ApiInterface.class);

            Call<JsonArray> call = apiInterface.getAccount();

            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(@NotNull Call<JsonArray> call, @NotNull Response<JsonArray> response) {
                    JsonArray res = response.body();




                    if (response.code() == 200) {

                        List<BankAccount> allBankAccount = new ArrayList<>();
                        for(int i = 0 ; i < res.size();i++){
                            JsonObject obj = res.get(i).getAsJsonObject();
                            allBankAccount.add(new BankAccount(
                                    obj.get("id").getAsInt(),
                                     obj.get("amount").getAsBigDecimal(),
                                    obj.get("account_name").getAsString(),
                                    obj.get("currency").getAsString(),
                                    obj.get("iban").getAsString()));
                        }

                        Intent intent = getIntent();
                        Users user = new Users(intent.getStringExtra("firstName"),intent.getStringExtra("lastName"),intent.getIntExtra("id",0),allBankAccount);


                        setUser(user);

                        if(!isRefresh){
                            setupFragment();
                        }
                        dialog.cancel();

                    } else {

                    }
                }

                @Override
                public void onFailure(@NotNull Call<JsonArray> call, @NotNull Throwable t) {
                    call.cancel();
                }
            });
        });
    }

    public void setupFragment(){
        Log.d("lol",this.user.toString());
        setContentView(R.layout.activity_home);
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeFragment(R.id.fragment_all_account);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        changeFragment(id);

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void changeFragment(int id){
        Bundle args = new Bundle();
        args.putSerializable("User",this.user);
        if (id == R.id.fragment_transfer) {
            if (this.fragmentTransfer == null) {
                this.fragmentTransfer = new TransferFragment();
            }
            if (!this.fragmentTransfer.isVisible()){
                this.fragmentTransfer.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_frame_layout, this.fragmentTransfer).commit();
            }
        }
        else  if (id == R.id.fragment_all_account) {
            if (this.fragmentAllAccount == null) {
                this.fragmentAllAccount = new AllAccountFragment();
            }
            if (!this.fragmentAllAccount.isVisible()){

                this.fragmentAllAccount.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_frame_layout, this.fragmentAllAccount).commit();
            }
        }
    }


    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }
}
