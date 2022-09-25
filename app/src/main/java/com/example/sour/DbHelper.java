package com.example.sour;

import android.content.ContentValues;
import android.content.Context;
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
    public void DeleteAll(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(USERS,null,null);
        sqLiteDatabase.close();
    }
    public void Add(String email, String passwd){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EMAIL,email);
        cv.put(PASSWORD,passwd);
        sqLiteDatabase.insert(USERS,null,cv);
        sqLiteDatabase.close();
    }
    public LinkedList GetAll(){
        List<Object> linkedList = new List<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(USERS,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do{
                int id_id = cursor.getColumnIndex(ID);
                int id_email = cursor.getColumnIndex(EMAIL);
                int id_passwd = cursor.getColumnIndex(PASSWORD);
            linkedList.add(cursor.getInt(id_id),cursor.getString(id_email),cursor.getString(id_passwd));

            }while(cursor.moveToNext());
            sqLiteDatabase.close();
        }
        return linkedList;
    }
}
