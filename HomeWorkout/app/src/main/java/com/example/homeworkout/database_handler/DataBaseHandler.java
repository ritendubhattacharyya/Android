package com.example.homeworkout.database_handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.homeworkout.data_handler.Advance;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private final static String DB_NAME = "workouts";
    private final static String TABLE_NAME = "exercise";
    private final static String COL_1 = "id";
    private final static String COL_2 = "days";
    private final static String COL_3 = "first_workout";
    private final static String COL_4 = "second_workout";
    private final static String COL_5 = "third_workout";
    private final static String COL_6 = "fourth_workout";
    private final static String COL_7 = "fifth_workout";

    public DataBaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NAME+"("+COL_1+" INTEGER PRIMARY KEY, "+COL_2+" VARCHAR(50), "+COL_3+" VARCHAR(50), "+COL_4+" VARCHAR(50), "+COL_5+" VARCHAR(50), "+COL_6+" VARCHAR(50), "+COL_7+" VARCHAR(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(SQLiteDatabase db, Advance advance) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, advance.getIndex());
        contentValues.put(COL_2, advance.getDay());
        contentValues.put(COL_3, advance.getFirst_workout());
        contentValues.put(COL_4, advance.getSecond_workout());
        contentValues.put(COL_5, advance.getThird_workout());
        contentValues.put(COL_6, advance.getFourth_workout());
        contentValues.put(COL_7, advance.getFifth_workout());

        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<Advance> getall(SQLiteDatabase db) {
        String sql = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        List<Advance> workout= new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Advance advance = new Advance(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));

                workout.add(advance);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return workout;
    }

    public void delete(SQLiteDatabase db, Advance advance) {
        String clause = COL_1 +"=?";
        String[] args = {String.valueOf(advance.getIndex())};

        db.delete(TABLE_NAME, clause, args);
    }
}
