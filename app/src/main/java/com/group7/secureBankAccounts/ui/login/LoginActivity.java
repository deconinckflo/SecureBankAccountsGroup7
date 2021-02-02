package com.group7.secureBankAccounts.ui.login;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.group7.secureBankAccounts.R;
import com.group7.secureBankAccounts.data.model.Cipher;
import com.group7.secureBankAccounts.data.model.Users;
import com.group7.secureBankAccounts.dataBase.UsersDao;
import com.group7.secureBankAccounts.ui.home.HomeActivity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;


public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void buttonConnection(View view) throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        EditText textViewUserFirstName = (EditText) findViewById(R.id.firstNameText);
        EditText textViewUserLastName = (EditText) findViewById(R.id.lastNameText);
        EditText textViewPassword = (EditText) findViewById(R.id.password);

        String firstName = textViewUserFirstName.getText().toString();
        String lastName = textViewUserLastName.getText().toString();
        String password = textViewPassword.getText().toString();


        UsersDao usersDao = new UsersDao();

        Users user = usersDao.getUserByFirstNameAndLastName(this, firstName, lastName);
        if (user == null) {

        } else {
            if(Cipher.validatePassword(password, user.getPassword())){
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("firstName",firstName);
                intent.putExtra("lastName",lastName);
                intent.putExtra("id",user.getId());
                startActivity(intent);

            }
            else{

            }

        }


    }


}