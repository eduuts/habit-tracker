package edu.habittrackerrunning.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edu on 7/19/2017.
 */

public class RunningDbHelper extends SQLiteOpenHelper {
    public RunningDbHelper(Context context) {
        super(context, RunningContract.DB_NAME, null, RunningContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE IF NOT EXISTS " + RunningContract.RunningEntry.TABLE + " (" + RunningContract.RunningEntry.TASK_RUNNING_LOCATION + " VARCHAR, " + RunningContract.RunningEntry.TASK_RUNNING_DISTANCE + " INT(3))";
        System.out.println("Create table " + createTable);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RunningContract.RunningEntry.TABLE);
        onCreate(db);
    }


    public void insert(String location) {

        int distance = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("Running location in DB Helper " + location);

        ContentValues values = new ContentValues();
        values.put(RunningContract.RunningEntry.TASK_RUNNING_LOCATION, location);
        values.put(RunningContract.RunningEntry.TASK_RUNNING_DISTANCE, distance);

        db.insertWithOnConflict(RunningContract.RunningEntry.TABLE,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);

        db.close();

    }


    public Cursor read() {


            SQLiteDatabase db = this.getWritableDatabase();



            Cursor c = db.query(RunningContract.RunningEntry.TABLE,null,null,null,null,null, null);

            int locationIndex = c.getColumnIndex(RunningContract.RunningEntry.TASK_RUNNING_LOCATION);
            int distanceIndex = c.getColumnIndex(RunningContract.RunningEntry.TASK_RUNNING_DISTANCE);

            if (c != null && c.moveToFirst()) {
                do {
                    System.out.println("READ location " + c.getString(locationIndex));
                    System.out.println("READ distance " + Integer.toString(c.getInt(distanceIndex)));
                    String habit = c.getString(locationIndex) + " : " + Integer.toString(c.getInt(distanceIndex));

                } while (c.moveToNext());
            }

            c.close();
        return c;
    }

}

