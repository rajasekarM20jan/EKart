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
    Cursor c;
    String thumbnail;
    int count;
    ArrayList image;

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
        ArrayList title2=new ArrayList<>();
        ArrayList img=new ArrayList<>();
        String title1=null;
        for(int j=0;j<a;j++){
            c.moveToNext();
            title1=c.getString(0);
            img.add(c.getString(1));
        }
        title2.add(title1);
        title2.add(img);
        image.add(title2);
    }

    void getThumbnail(String title){
        dbReader =this.getReadableDatabase();
        c=dbReader.rawQuery("SELECT thumbnail FROM products WHERE title LIKE ?",new String[]{"%"+title+"%"});
        c.moveToNext();
        thumbnail=c.getString(0);

    }
}
