package com.example.sisonkebank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    bankDAO bankdatabase;
    EditText fname,lastname,email,phone,password;
    RadioGroup radioGroup;
    RadioButton genderClicked;
    Button createAccountBtn,alreadyHaveAccount;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = findViewById(R.id.registerToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sisonke Bank App");

        final Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        bankdatabase = new bankDAO(this);
        fname = findViewById(R.id.firstname);
        lastname= findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.mobile);
        createAccountBtn = findViewById(R.id.createAccount);
        radioGroup = findViewById(R.id.radioButtonsGroup);
        alreadyHaveAccount = findViewById(R.id.btngotToLogin);


        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedGender = radioGroup.getCheckedRadioButtonId();
                genderClicked = (RadioButton)findViewById(selectedGender);
                boolean inserted = bankdatabase.insertData(email.getText().toString(),fname.getText().toString(), lastname.getText().toString(),phone.getText().toString(),genderClicked.getText().toString(),password.getText().toString());
                if(inserted){
                    Toast.makeText(RegisterActivity.this,"Account Created Successfully",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this,"Account not Created",Toast.LENGTH_LONG).show();
                    //do error handling here
                }
            }
        });

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(intent);
               finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
