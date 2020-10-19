package com.example.sisonkebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageView viewImage;
    EditText email,password;
    Button login,registerButton;
    bankDAO bankdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bankdatabase = new bankDAO(this);

        viewImage = findViewById(R.id.mainBankImage);
        email = findViewById(R.id.email);
        password =findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerbutton);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = bankdatabase.getData(email.getText().toString(),password.getText().toString());
                if(res.getCount() == 1){
                    bankAccount getUserInfo = new bankAccount();
                    while(res.moveToNext()){
                        getUserInfo.setEmailAddress(res.getString(0));
                        getUserInfo.setUsername(res.getString(1));
                        getUserInfo.setUserSurname(res.getString(2));
                        getUserInfo.setPhoneNumber(res.getString(3));
                        getUserInfo.setAmountCurrent(Double.parseDouble(res.getString(5)));
                        getUserInfo.setAmountSavings(Double.parseDouble(res.getString(6)));
                        getUserInfo.setGender(res.getString(4));
                    }


                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    intent.putExtra("userInfo",getUserInfo);
                    setResult(LoginActivity.RESULT_OK,intent);
                    Log.i("infomation ",getUserInfo.toString());
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Email or Password Wrong!",Toast.LENGTH_LONG).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent resgisterIntent = new Intent(LoginActivity.this,RegisterActivity.class);
               startActivity(resgisterIntent);
               finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
