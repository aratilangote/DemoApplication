package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText FirstName, LastName, Email, Password, retypePassword, Address;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        FirstName = findViewById(R.id.firstname);
        LastName = findViewById(R.id.lastname);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retype_password);
        Address = findViewById(R.id.address);
        login = findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Firstname = FirstName.getText().toString();
                String Lastname = LastName.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String Retypepassword = retypePassword.getText().toString();
                String address = Address.getText().toString();

                Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}