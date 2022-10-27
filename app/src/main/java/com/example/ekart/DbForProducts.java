package com.example.ekart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbForProducts extends SQLiteOpenHelper {
    SQLiteDatabase dbReader,dbWriter;
    ContentValues values;
    Cursor c,d;
    int count;
    String data;
    ArrayList image;
    String id,title,description,price,discountPercentage,rating,stock,brand,category,thumbnail;;

    public DbForProducts(@Nullable Context context) {
        super(context, "productDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("CREATE TABLE products(id text,title text,description text, price text, discountPercentage text,rating text,stock text, brand text,category text,thumbnail text)");
        myDb.execSQL("CREATE TABLE productImages(title text,images text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("DROP TABLE IF EXISTS products");
        onCreate(myDb);
    }


    void getCount(){
        dbReader=this.getReadableDatabase();
        c=dbReader.rawQuery("SELECT id FROM products",null);
        count=c.getCount();
    }



    void insert(String id,String title,String description,String price,String discountPercentage,String rating,String stock,String brand,String category,String thumbnail){
        try{
            values=new ContentValues();
            values.put("id",id);
            values.put("title",title);
            values.put("description",description);
            values.put("price",price);
            values.put("discountPercentage",discountPercentage);
            values.put("rating",rating);
            values.put("stock",stock);
            values.put("brand",brand);
            values.put("category",category);
            values.put("thumbnail",thumbnail);
            dbWriter=this.getWritableDatabase();
            dbWriter.insert("products",null,values);

        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    void insertImage(String title,String image){
        values=new ContentValues();
        values.put("title",title);
        values.put("images",image);
        dbWriter=this.getWritableDatabase();
        dbWriter.insert("productImages",null,values);
    }

    void getImages(String title){
        image=new ArrayList<>();
        dbReader =this.getReadableDatabase();
        c=dbReader.rawQuery("SELECT * FROM productImages WHERE title LIKE ?",new String[]{"%"+title+"%"});
        int a=c.getCount();
        ArrayList img=new ArrayList<>();
        String title1=null;
        for(int j=0;j<a;j++){
            c.moveToNext();
            title1=c.getString(0);
            img.add(c.getString(1));
        }
        image.add(title1);
        image.add(img);
    }

    void getThumbnail(String title){
        dbReader =this.getReadableDatabase();
        c=dbReader.rawQuery("SELECT thumbnail FROM products WHERE title LIKE ?",new String[]{"%"+title+"%"});
        c.moveToNext();
        thumbnail=c.getString(0);

    }
    void getData(String product){
        dbReader =this.getReadableDatabase();
        c=dbReader.rawQuery("SELECT * FROM products WHERE title LIKE ?",new String[]{"%"+product+"%"});
        c.moveToNext();
        id=c.getString(0);
        title=c.getString(1);
        description=c.getString(2);
        price=c.getString(3);
        discountPercentage=c.getString(4);
        rating=c.getString(5);
        stock=c.getString(6);
        brand=c.getString(7);
        category=c.getString(8);
        thumbnail=c.getString(9);

    }


    void getList(String id){
        dbReader=this.getReadableDatabase();
        c=dbReader.rawQuery("SELECT * FROM products WHERE id=?",new String[]{id});
        c.moveToNext();
        this.id=c.getString(0);
        title=c.getString(1);
        description=c.getString(2);
        price=c.getString(3);
        discountPercentage=c.getString(4);
        rating=c.getString(5);
        stock=c.getString(6);
        brand=c.getString(7);
        category=c.getString(8);
        thumbnail=c.getString(9);
    }
}
