package dmm.kobietapewnasiebie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import dmm.kobietapewnasiebie.database.KPScontract;
import dmm.kobietapewnasiebie.database.KPSdataHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by ddabrowa on 2017-10-02.
 */

public class Databasetest {

    private final Context applicationContext = InstrumentationRegistry.getTargetContext();
    private final Class databaseHelperClass = KPSdataHelper.class;

    @Before
    public void prepareDatabase(){
        deleteDatabase();
    }

    @Test
    public void applicationOpensDatabase() throws Exception{
        SQLiteDatabase database = openDatabase();
        assertEquals(true,database.isOpen());
    }

    @Test
    public void applicationCreatesHelloMessageTable() throws Exception{
        SQLiteDatabase database = openDatabase();
        Cursor activityTableNameCursor = database.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='" +
                        KPScontract.HelloMessages.TABLE_NAME + "'",
                null);
        assertTrue(activityTableNameCursor.moveToFirst());
        assertEquals(KPScontract.HelloMessages.TABLE_NAME,activityTableNameCursor.getString(0));
        activityTableNameCursor.close();
    }


    @Test
    public void insertToHelloMessageTableIsPossible() throws Exception{
        SQLiteDatabase database = openDatabase();
        ContentValues testValues = new ContentValues();
        testValues.put(KPScontract.HelloMessages.COLUMN_HELLO_TEXT,"hello text");
        testValues.put(KPScontract.HelloMessages.COLUMN_TEXT_TYPE,"text type");
        testValues.put(KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT, "author");
        testValues.put(KPScontract.HelloMessages.COLUMN_WHEN_USED,2017-10-02);
        long firstRowId = database.insert(KPScontract.HelloMessages.TABLE_NAME,null,testValues);
        Cursor cursor = getCursorByQuery(database,KPScontract.HelloMessages.TABLE_NAME);
        assertNotEquals(-1,firstRowId);
        assertTrue(cursor.moveToFirst());
        cursor.close();
    }

    @Test
    public void updateDatabaseIsPossible() throws Exception{
        SQLiteDatabase database = openDatabase();
        ContentValues testValues = new ContentValues();
        testValues.put(KPScontract.HelloMessages.COLUMN_HELLO_TEXT,"hello text");
        testValues.put(KPScontract.HelloMessages.COLUMN_TEXT_TYPE,"text type");
        testValues.put(KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT, "author");
        testValues.put(KPScontract.HelloMessages.COLUMN_WHEN_USED,"2017-10-02");
        database.insert(KPScontract.HelloMessages.TABLE_NAME,null,testValues);
        testValues.put(KPScontract.HelloMessages.COLUMN_HELLO_TEXT,"hello text");
        testValues.put(KPScontract.HelloMessages.COLUMN_TEXT_TYPE,"text type");
        testValues.put(KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT, "author");
        testValues.put(KPScontract.HelloMessages.COLUMN_WHEN_USED,"2017-10-02");
        database.insert(KPScontract.HelloMessages.TABLE_NAME,null,testValues);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        testValues = new ContentValues();
        Cursor cursor = getCursorByQuery(database,KPScontract.HelloMessages.TABLE_NAME);
        cursor.moveToPosition(0);

        testValues.put(KPScontract.HelloMessages.COLUMN_HELLO_TEXT,"not hello");
        testValues.put(KPScontract.HelloMessages.COLUMN_TEXT_TYPE,"text type");
        testValues.put(KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT, "author");
        testValues.put(KPScontract.HelloMessages.COLUMN_WHEN_USED, calendar.getTime().toString());
        int ingex = cursor.getInt(cursor.getColumnIndex(KPScontract.HelloMessages._ID));
        database.update(KPScontract.HelloMessages.TABLE_NAME,testValues, KPScontract.HelloMessages._ID + " = " + ingex,null);

        cursor = getCursorByQuery(database,KPScontract.HelloMessages.TABLE_NAME);
        cursor.moveToPosition(0);

        assertEquals("not hello",cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_HELLO_TEXT)));
        assertEquals(calendar.getTime().toString(),cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_WHEN_USED)));
        cursor.close();
    }

    private SQLiteDatabase openDatabase() throws Exception {
        SQLiteOpenHelper dbHelperConstructor = (SQLiteOpenHelper) databaseHelperClass.getConstructor(Context.class).newInstance(applicationContext);
        return dbHelperConstructor.getWritableDatabase();
    }

    private Cursor getCursorByQuery(SQLiteDatabase database, String tableName) throws Exception{
        return database.query(tableName,null,null,null,null,null,null);
    }

    private void deleteDatabase(){
        try {
            Field tableFields = databaseHelperClass.getDeclaredField("DATABASE_NAME");
            tableFields.setAccessible(true);
            applicationContext.deleteDatabase((String)tableFields.get(null));
        }catch (NoSuchFieldException ex){
            fail("Make sure you have a member called DATABASE_NAME in the KPSdataHelper");
        }catch (Exception ex){
            fail(ex.getMessage());
        }
    }
}
