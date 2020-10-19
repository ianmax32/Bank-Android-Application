package com.example.sisonkebank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.ls.LSOutput;

public class WelcomeActivity extends AppCompatActivity {

    TextView name;
    Button viewbalance,transfer,logout;
    Toolbar toolbar;
    bankDAO bankdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        toolbar = findViewById(R.id.welcomeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sisonke Bank App");

        name = (TextView) findViewById(R.id.welcomename);
        viewbalance = findViewById(R.id.viewaccountbalance);
        transfer = findViewById(R.id.transfer);
        logout = findViewById(R.id.logout);
        bankdatabase = new bankDAO(this);


        Cursor res = bankdatabase.getData();
        while(res.moveToNext()){
            name.setText(res.getString(1));
        }

        viewbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewAccountIntent = new Intent(WelcomeActivity.this, ViewAccountBalanceActivity.class);
                setResult(RESULT_OK,viewAccountIntent);
                startActivity(viewAccountIntent);
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tranferIntent = new Intent(WelcomeActivity.this, TransferActivity.class);
                setResult(RESULT_OK,tranferIntent);
                startActivity(tranferIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                logoutIntent.removeExtra("userInfo");
                setResult(RESULT_OK,logoutIntent);
                Toast.makeText(WelcomeActivity.this,"Logout Successfully",Toast.LENGTH_SHORT).show();
                startActivity(logoutIntent);
                finish();
            }
        });

    }


}
