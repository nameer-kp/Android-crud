package com.example.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating a sql statement for creation of the named CUSTOMER_TABLE
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CUSTOMER_NAME + " TEXT," + COLUMN_CUSTOMER_AGE + " INT," + COLUMN_ACTIVE_CUSTOMER + " BOOL)";
        //this will execute sql statement on the database
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne (CustomerModel customerModel){
        //getting database to write
        SQLiteDatabase db=this.getWritableDatabase();

        //this object holdes the contents
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME,customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE,customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER,customerModel.isActive());

        long insert=  db.insert(CUSTOMER_TABLE,null,cv);
        //insert hold -1 if the insertion is failed
        if(insert==-1){
            return false;

        }
        else {
            return true;
        }
    }
    public boolean deleteOne(CustomerModel customerModel){
        SQLiteDatabase db=this.getWritableDatabase();
        //writing sql query for deleting
        String sqlQuery ="DELETE * FROM "+CUSTOMER_TABLE+" WHERE "+COLUMN_ID+" = "+customerModel.getId();
        Cursor cursor = db.rawQuery(sqlQuery,null);
        if(cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }
    public List<CustomerModel> getList(){
        List<CustomerModel> returnList = new ArrayList<>();
        String sqlQuery ="SELECT * FROM "+CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor is the type of data returns by sqlite
        Cursor cursor =db.rawQuery(sqlQuery,null);

        //check if the cursor has the first item
        if(cursor.moveToFirst()){
            do{
                //fetching details from the cursor
                int customerId = cursor.getInt(0);
                String customerName= cursor.getString(1);
                int customerAge = cursor.getInt(2);
                Boolean isActive = cursor.getInt(3) == 1;

                CustomerModel newCustomer = new CustomerModel(customerId,customerName,customerAge,isActive);

                returnList.add(newCustomer);

            }while (cursor.moveToNext());
        }
        //close connections
        //close cursor
        //close db

        cursor.close();
        db.close();
        return returnList;
    }

}
