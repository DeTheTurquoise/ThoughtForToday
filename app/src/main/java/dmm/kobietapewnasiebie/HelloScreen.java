package dmm.kobietapewnasiebie;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import dmm.kobietapewnasiebie.database.DefaultData.HelloMessageDefaultData;
import dmm.kobietapewnasiebie.database.KPScontract;
import dmm.kobietapewnasiebie.database.KPSdataHelper;
import dmm.kobietapewnasiebie.databinding.ActivityHelloScreenBinding;
import dmm.kobietapewnasiebie.utils.Tags;

public class HelloScreen extends AppCompatActivity {

    private SQLiteDatabase database;
    private String[] columns = {KPScontract.HelloMessages.COLUMN_HELLO_TEXT,KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT,KPScontract.HelloMessages._ID};
    private SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
    ActivityHelloScreenBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_hello_screen);
        prepareScreen();
        int dayName = getDayOfWeek(getCalendarForToday());
        if(!setDataWhichWasUsedToday()){
            Calendar calendar = getCalendarForToday();
            Cursor queryResult = getDataWhichWasNotUsed(Tags.getTag(dayName));
            int id = setRandomMessage(queryResult);
            addDateOfUsingDataToDatabase(calendar,id);
        }
    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }

    private void prepareScreen(){
        KPSdataHelper helper = new KPSdataHelper(this);
        database = helper.getWritableDatabase();
        Cursor cursor = database.query(KPScontract.HelloMessages.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.getCount()==0){
            HelloMessageDefaultData.insertDefaultData(database);
        }

        Typeface face=Typeface.createFromAsset(getAssets(), "Garineldo.ttf");
        activityMainBinding.helloMessageText.setTypeface(face);
        face = Typeface.createFromAsset(getAssets(), "Raleway-SemiBoldItalic.ttf");
        activityMainBinding.additionalText.setTypeface(face);
    }

    public void setTextToFields(String... textToDisplay) {
        clearTextFields();
        activityMainBinding.helloMessageText.setText(textToDisplay[0]);
        if (textToDisplay.length>1) {
            activityMainBinding.additionalText.setText(textToDisplay[1]);
        }
    }

    public String[] getTextFromFields(){
        String[] addedTexts = {};
        addedTexts[0] = activityMainBinding.helloMessageText.getText().toString();
        addedTexts[1] = activityMainBinding.additionalText.getText().toString();
        return addedTexts;
    }

    private void clearTextFields(){
        activityMainBinding.helloMessageText.setText("");
        activityMainBinding.helloMessageText.setText("");
    }

    private Cursor getDataWhichWasNotUsed(String tag){
        return database.query(
                KPScontract.HelloMessages.TABLE_NAME,
                columns,
                KPScontract.HelloMessages.COLUMN_TEXT_TYPE + " = '" + tag + "' AND " + KPScontract.HelloMessages.COLUMN_WHEN_USED + " IS NULL",
                null,
                null,
                null,
                null
        );
    }

    private boolean setDataWhichWasUsedToday(){
        Calendar calendar = getCalendarForToday();
        Cursor cursor = database.query(
                KPScontract.HelloMessages.TABLE_NAME,
                columns,
                KPScontract.HelloMessages.COLUMN_WHEN_USED + " = '" + dateFormat.format(calendar.getTime()) + "'",
                null,
                null,
                null,
                null
        );
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            setTextToFields(cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_HELLO_TEXT)),cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT)));
            return true;
        }
        else{
            return false;
        }
    }

    private void addDateOfUsingDataToDatabase(Calendar calendar, int messageId){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KPScontract.HelloMessages.COLUMN_WHEN_USED, dateFormat.format(calendar.getTime()));
        database.update(KPScontract.HelloMessages.TABLE_NAME,contentValues, KPScontract.HelloMessages._ID + " = " + messageId,null);
    }

    private Calendar getCalendarForToday(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }


    private int setRandomMessage(Cursor cursor){
        Random randomNumber = new Random();
        int maxNumber = cursor.getCount();
        int randomItem = randomNumber.nextInt(maxNumber);

        cursor.moveToPosition(randomItem);
        setTextToFields(cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_HELLO_TEXT)),cursor.getString(cursor.getColumnIndex(KPScontract.HelloMessages.COLUMN_ADDITIONAL_TEXT)));

        return cursor.getInt(cursor.getColumnIndex(KPScontract.HelloMessages._ID));
    }

    // 1-Sunday,2-Monday, ...
    private int getDayOfWeek(Calendar calendar){
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i("temp", "Day - " + day + ", date - " + calendar.getTime());
        return day;
    }
}
