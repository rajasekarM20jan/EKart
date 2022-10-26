package com.example.ekart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbForUser extends SQLiteOpenHelper {
    String name,userName,email,password,mobileNumber,login;
    SQLiteDatabase dbReader,dbWriter;
    ContentValues values;
    Cursor c;
    String userFound;
    ArrayList cart;

    public DbForUser(@Nullable Context context) {
        super(context, "UserDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("CREATE TABLE userAccounts (name text,userName text,email text,password text,mobileNumber text,login text)");
        myDb.execSQL("CREATE TABLE userCart(mobileNumber text,cart text)");
        myDb.execSQL("CREATE TABLE userAddress(mobileNumber text,address text)");
        myDb.execSQL("CREATE TABLE userOrders(mobileNumber text,orders text,orderDate text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("DROP TABLE IF EXISTS userAccounts");
        onCreate(myDb);
    }
    public void addNewUser(String name,String userName,String email,String password,String mobileNumber,String login){
        dbWriter=this.getWritableDatabase();
        values=new ContentValues();
        values.put("name",name);
        values.put("userName",userName);
        values.put("email",email);
        values.put("password",password);
        values.put("mobileNumber",mobileNumber);
        values.put("login",login);
        dbWriter.insert("UserAccounts",null,values);
    }
    public void verify(String userAccess,String pass){
        dbReader=this.getReadableDatabase();
        try{
            c= dbReader.rawQuery("SELECT * FROM UserAccounts WHERE mobileNumber=?",new String[]{userAccess});
            c.moveToNext();
            name=c.getString(0);
            userName=c.getString(1);
            email=c.getString(2);
            password=c.getString(3);
            mobileNumber=c.getString(4);
            login=c.getString(5);
            userFound="true";
            c.close();
        }catch (Exception e){
            userFound="false";
        }
    }
    public void getData(String mobile) {
        try{
            dbReader = this.getReadableDatabase();
            c = dbReader.rawQuery("SELECT * FROM userAccounts WHERE mobileNumber=? ", new String[]{mobile});
            c.moveToNext();
            name = c.getString(0);
            userName = c.getString(1);
            email = c.getString(2);
            password = c.getString(3);
            mobileNumber = c.getString(4);
            login = c.getString(5);
            c.close();


        }catch(Exception e){
            login="false";
        }

    }
    public void updateData(String mobile,String loginStatus){
        try{
            getData(mobile);
            dbWriter=this.getWritableDatabase();
            values=new ContentValues();
            values.put("name",name);
            values.put("userName",userName);
            values.put("email",email);
            values.put("password",password);
            values.put("mobileNumber",mobileNumber);
            values.put("login",loginStatus);
            dbWriter.update("userAccounts",values,"mobileNumber=?",new String[]{mobile});
        }catch (Exception e){
            System.out.println("ERROR AT UPDATE");
        }
    }
    public void insertCart(String mobileNumber,String cart){
        dbWriter=this.getWritableDatabase();
        values=new ContentValues();
        values.put("mobileNumber",mobileNumber);
        values.put("cart",cart);
        dbWriter.insert("userCart",null,values);
    }
    public void getCart(String mobileNumber){
        dbReader=this.getReadableDatabase();
        c = dbReader.rawQuery("SELECT * FROM userCart WHERE mobileNumber=? ", new String[]{mobileNumber});
        cart=new ArrayList<>();
        while (c.moveToNext()){
            String a=c.getString(1);
            cart.add(a);
        }
    }
    public void deleteFromCart(String mobile,String cart){
        dbWriter=this.getWritableDatabase();
        dbWriter.delete("userCart","mobileNumber=? AND cart=?",new String[]{mobile,cart});
    }

    public void insertOrders(String mobileNumber,String order,String orderDate){

    }
}
