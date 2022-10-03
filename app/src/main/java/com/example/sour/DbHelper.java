package com.example.sour;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String USERS = "Users";

    private static final String ID = "Id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    public String createTables = "CREATE table " + USERS + "(" +
            ID + " INTEGER NOT NULL primary key autoincrement," +
            EMAIL + " Varchar(255)," +
            PASSWORD + " Varchar(255)" +
            ")";
    public String DropTable = "DROP table " + USERS;
    public DbHelper(@Nullable Context context ) {
        super(context,  "example", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL(DropTable);
            onCreate(sqLiteDatabase);
        }

    }
    @SuppressLint("Range")
    public boolean Login(String email, String password){
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("select * from Users where email = ? AND password = ?", new String[]{email, password});
            return c.getCount() != 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public void Register(String email, String passwd){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EMAIL,email);
        cv.put(PASSWORD,passwd);
        sqLiteDatabase.insert(USERS,null,cv);
        sqLiteDatabase.close();
    }
    public void DeleteOne(String Id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(USERS,ID+"= ?",new String[]{Id});
        sqLiteDatabase.close();
    }

    public LinkedList<Data> GetAll(){
        LinkedList<Data> list = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(USERS,null,null,null,null,null,null,null);

        if (cursor.moveToFirst())
            do {
                int id = cursor.getColumnIndex(ID);
                int mail = cursor.getColumnIndex(EMAIL);
                int passwd = cursor.getColumnIndex(PASSWORD);

                Data data = new Data(cursor.getInt(id),cursor.getString(mail),cursor.getString(passwd));
                list.add(data);
            }while (cursor.moveToNext());
            sqLiteDatabase.close();
            return list;
    }
    public LinkedList<Data> GetOne(String Id){
        LinkedList<Data> list = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+USERS+" where "+ID+" = ?", new String[] { Id });

        if (cursor.moveToFirst())
            do {
                int id = cursor.getColumnIndex(ID);
                int mail = cursor.getColumnIndex(EMAIL);
                int passwd = cursor.getColumnIndex(PASSWORD);

                Data data = new Data(cursor.getInt(id),cursor.getString(mail),cursor.getString(passwd));
                list.add(data);
            }while (cursor.moveToNext());
        sqLiteDatabase.close();
        return list;
    }
    public void UpdateOne(String id, String mail, String passwd){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID, id);
        cv.put(EMAIL, mail);
        cv.put(PASSWORD, passwd);
        db.update(USERS, cv,ID+"= ?", new String[]{id});
    }






























    public void Update(String id, String email, String passwd){

    }


}
