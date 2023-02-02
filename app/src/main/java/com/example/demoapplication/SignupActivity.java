package com.example.demoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    EditText FirstName, LastName, Email, Mobile, Password, retypePassword, Address;
    Button BtnCreateAcc, verify;

    private FirebaseAuth fAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fAuth = FirebaseAuth.getInstance();

        FirstName = findViewById(R.id.firstname);
        LastName = findViewById(R.id.lastname);
        Email = findViewById(R.id.email);
        Mobile = findViewById(R.id.mobile);
        Password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retype_password);
        Address = findViewById(R.id.address);

        BtnCreateAcc = findViewById(R.id.btn_create_acc);
        verify = findViewById(R.id.sendotp);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, VerifyAndSignInActivity.class);
                intent.putExtra("mobile_no", Mobile.getText().toString());
                startActivity(intent);

            }
        });

        BtnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = FirstName.getText().toString();
                String lname = LastName.getText().toString();
                String mail = Email.getText().toString();
                String phone = Mobile.getText().toString();
                String pass = Password.getText().toString();
                String rePass = retypePassword.getText().toString();
                String address = Address.getText().toString();

                if (TextUtils.isEmpty(FirstName.getText().toString())){
                    FirstName.setError("Please enter first name");
                } else if (TextUtils.isEmpty(LastName.getText().toString())) {
                    LastName.setError("Please enter first name");
                } else if (TextUtils.isEmpty(Email.getText().toString())) {
                    Email.setError("Please enter email");
                }else if (TextUtils.isEmpty(Mobile.getText().toString())){
                    Mobile.setError("Please enter mobile number");
                } else if (Mobile.getText().toString().matches("^[0-9]{10}$")) {
                    Mobile.setError("Please enter valid mobile number");
                } else if (TextUtils.isEmpty(Password.getText().toString())) {
                    Password.setError("Please enter password");
                } else if (Password.getText().toString().length() <= 6) {
                    Password.setError("Password length should be greater than or equal to 6");
                } else if (TextUtils.isEmpty(retypePassword.getText().toString())) {
                    retypePassword.setError("Please re-enter password");
                } else if (Password.getText().toString() != retypePassword.getText().toString()) {
                    retypePassword.setError("Confirm password must be same as password");
                } else if (TextUtils.isEmpty(Address.getText().toString())) {
                    Address.setError("Please enter address");
                } else if (!TextUtils.isEmpty(FirstName.getText().toString()) && !TextUtils.isEmpty(LastName.getText().toString()) &&
                            !TextUtils.isEmpty(Email.getText().toString()) && !TextUtils.isEmpty(Mobile.getText().toString()) &&
                            !TextUtils.isEmpty(Password.getText().toString()) && !TextUtils.isEmpty(retypePassword.getText().toString()) &&
                            !TextUtils.isEmpty(Address.getText().toString())) {

                    fAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);

                            // If sign-in fails, display a message to the user. If sign-in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_LONG).show();
                                Log.e("MyTag", task.getException().toString());
                            } else {
                                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                                finish();
                            }
                        }
                    });


                } else{
                    Toast.makeText(SignupActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}