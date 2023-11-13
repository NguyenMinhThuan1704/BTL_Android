package com.example.appdecorativelight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.appdecorativelight.Models.OrdersModel;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final static String DBName = "mydatabase.db";
    final static int DBVERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "Create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "description text," +
                        "lightname text," +
                        "quantity int)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String name, String phone, int price, int image, String desc, String lightname, int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id = 0
        name = 1
        phone = 2
        price = 3
        image = 4
        desc = 5
        lightname = 6
        quantity = 7
         */
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("lightname", lightname);
        values.put("quantity", quantity);

        long id = database.insert("orders", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select id, lightname, image, price from orders",null);
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                //OrdersModel model = new OrdersModel();
                OrdersModel model = new OrdersModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3) + "");
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from orders where id = "+id,null);

        if (cursor!=null)
            cursor.moveToFirst();

        return cursor;
    }

    public boolean updateOrder(String name, String phone, int price, int image, String desc, String lightname, int quantity, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id = 0
        name = 1
        phone = 2
        price = 3
        image = 4
        desc = 5
        lightname = 6
        quantity = 7
         */
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("lightname", lightname);
        values.put("quantity", quantity);

        long row = database.update("orders", values, "id ="+id, null);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int deletedOrder (String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id=" + id, null);
    }
}
