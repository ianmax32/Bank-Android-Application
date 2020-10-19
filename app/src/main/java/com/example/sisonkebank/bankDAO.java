package com.example.sisonkebank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class bankDAO extends SQLiteOpenHelper {

    private static bankAccount useraccount = new bankAccount();
    private static final String database_name = "sisonkeBank.db";
    private static final String table_name = "users_table";
    private static final String userID = "userId";
    private static final String username= "username";
    private static final String usersurname= "usersurname";
    private static final String userPhoneNumber = "phoneNumber";
    private static final String userGender = "gender";
    private static final  String amountCurrent = "currentAmount";
    private static final String amountSavings= "savingsAccount";
    private static final String password = "password";


    public bankDAO(@Nullable Context context) {
        super(context, database_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ table_name+"("+userID+" varchar(20) primary key,"+username+" varchar(20) not null,"+usersurname+" varchar(20) not null,"+userPhoneNumber+" varchar(20) not null,"+userGender+" varchar(20) not null,"
                +amountCurrent+" float not null,"+amountSavings+" float not null,"+password+" varchar(20) not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String userID,String username,String usersurname, String userPhoneNumber, String userGender,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId",userID);
        contentValues.put("username",username);
        contentValues.put("usersurname",usersurname);
        contentValues.put("phoneNumber",userPhoneNumber);
        contentValues.put("gender",userGender);
        contentValues.put("password",password);
        contentValues.put("currentAmount",5000);
        contentValues.put("savingsAccount",3000);
        long result = db.insert(table_name,null,contentValues);
        if(result == -1){
            return  false;
        }else{
            return true;
        }

    }

    public Cursor getData(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name+" where userID = '"+email+"' and password='"+password+"'",null);
        return res;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name+"",null);
        return res;
    }

    public boolean updateData(String userid, double currentamount, double savingsccount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("currentAmount",currentamount);
        contentValues.put("savingsAccount",savingsccount);

        db.update(table_name,contentValues,"userId = '"+userid+"'",null);

        return  true;
    }
}
