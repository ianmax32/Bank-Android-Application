package com.example.sisonkebank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TransferActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView accountbalance,savingsaccount;
    EditText amountToTranfer;
    Button tranfer;
    Spinner spin;
    bankAccount userInfo;
    Toolbar toolbar;
    String accountFrom = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        final bankDAO bankdatabase = new bankDAO(this);

        toolbar = findViewById(R.id.transferToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sisonke Bank App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String accountOption[] = {"Savings Account","Current Account"};
        accountbalance = findViewById(R.id.acountbalance);
        savingsaccount = findViewById(R.id.savingsaccountbalance);
        amountToTranfer = findViewById(R.id.amounttotransfer);
        spin = findViewById(R.id.tranferfrom);
        tranfer = findViewById(R.id.transfer);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,accountOption);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);


        final TextView email = new TextView(this);
        Cursor res = bankdatabase.getData();
        while(res.moveToNext()){
            accountbalance.setText(res.getString(5));
            savingsaccount.setText(res.getString(6));
            email.setText(res.getString(0));
        }

        tranfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //manipulate database to update the info
                double savingsBalance = Double.parseDouble(savingsaccount.getText().toString());
                double currentBalance =Double.parseDouble(accountbalance.getText().toString());;
                double newSavings = 0;
                double newCurrent= 0;
                if(accountFrom.equals("Savings Account")){
                    newSavings = savingsBalance - Double.parseDouble(amountToTranfer.getText().toString());
                    newCurrent = currentBalance + Double.parseDouble(amountToTranfer.getText().toString());

                }else{
                    newSavings = savingsBalance + Double.parseDouble(amountToTranfer.getText().toString());
                    newCurrent = currentBalance - Double.parseDouble(amountToTranfer.getText().toString());
                }

                if(bankdatabase.updateData(email.getText().toString(),newCurrent,newSavings)){
                    Cursor res = bankdatabase.getData();
                        while(res.moveToNext()){
                            accountbalance.setText(res.getString(5));
                            savingsaccount.setText(res.getString(6));
                        }
                    Toast.makeText(TransferActivity.this,"Tranfer Conmplete!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(TransferActivity.this,"Error Tranfering try again later!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        accountFrom = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
