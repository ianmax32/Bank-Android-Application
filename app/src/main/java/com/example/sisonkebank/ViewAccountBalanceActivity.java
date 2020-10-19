package com.example.sisonkebank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ViewAccountBalanceActivity extends AppCompatActivity {

    TextView accountholdername,accountholderSurname,currentBalance,savingsbalance;
    bankAccount moreUserInfo;
    Toolbar toolbar;
    bankDAO bankdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account_balance);
         bankdatabase = new bankDAO(this);
        toolbar = findViewById(R.id.viewAccountToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sisonke Bank App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        accountholdername= findViewById(R.id.accountholdername);
        accountholderSurname = findViewById(R.id.accountholdersurname);
        currentBalance = findViewById(R.id.acountbalance);
        savingsbalance = findViewById(R.id.savingsaccountbalance);

        Cursor res = bankdatabase.getData();
        while(res.moveToNext()){
            accountholdername.setText(res.getString(1));
            accountholderSurname.setText(res.getString(2));
            currentBalance.setText(String.valueOf(Double.parseDouble(res.getString(5))));
            savingsbalance.setText(String.valueOf(Double.parseDouble(res.getString(6))));
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
