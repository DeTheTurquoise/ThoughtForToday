package dmm.kobietapewnasiebie.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ddabrowa on 2017-09-29.
 */

public class KPSdataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "KPS.db";
    private static final int DATABASE_VERSION = 1;

    public KPSdataHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_HELLO_MESSAGE_TABLE = "CREATE TABLE " +
                KPScontract.HelloMessages.TABLE_NAME + " (" +
                KPScontract.HelloMessages._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KPScontract.HelloMessages.COLUMN_HELLO_TEXT + " TEXT NOT NULL, " +
                KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT + " TEXT, " +
                KPScontract.HelloMessages.COLUMN_TEXT_TYPE + " TEXT NOT NULL, " +
                KPScontract.HelloMessages.COLUMN_WHEN_USED + " TEXT" + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_HELLO_MESSAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + KPScontract.HelloMessages.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void printDatabase(SQLiteDatabase database){
        String[] allColumns = {
                KPScontract.HelloMessages._ID,
                KPScontract.HelloMessages.COLUMN_HELLO_TEXT,
                KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT,
                KPScontract.HelloMessages.COLUMN_TEXT_TYPE,
                KPScontract.HelloMessages.COLUMN_WHEN_USED
        };
        Cursor cursor = database.query(
                KPScontract.HelloMessages.TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            Log.i("DB",
                    "ID - " + cursor.getInt(cursor.getColumnIndex(KPScontract.HelloMessages._ID)) +
                            "| GT - " + cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_HELLO_TEXT)) +
                            "| AT - " + cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT)) +
                            "| TT - " + cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_TEXT_TYPE)) +
                            "| WU - " + cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_WHEN_USED)));
            cursor.moveToNext();
        }
    }
}
