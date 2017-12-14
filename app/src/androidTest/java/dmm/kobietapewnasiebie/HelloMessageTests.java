package dmm.kobietapewnasiebie;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import dmm.kobietapewnasiebie.database.KPSdataHelper;

import static org.junit.Assert.assertEquals;


/**
 * Created by ddabrowa on 2017-10-03.
 */

public class HelloMessageTests {

    private final Context applicationContext = InstrumentationRegistry.getTargetContext();
    private final Class databaseHelperClass = KPSdataHelper.class;
    private HelloScreen screen;

    @Before
    public void prepareScreen(){
//        Intent intent = new Intent(this,HelloScreen.class);
//        screen = applicationContext
    }

    @Test
    public void applicationDisplaysTextWithAdditionalTextOnLayout(){
        //given
        String testGeneralText = "This is Default General Text";
        String testAdditionalText = "This is Default Additional Text";
        //when
        screen.setTextToFields(testGeneralText,testAdditionalText);
        //then
        assertEquals(testGeneralText,screen.getTextFromFields()[0]);
        assertEquals(testAdditionalText,screen.getTextFromFields()[1]);
    }

    @Test
    public void applicationDisplaysTextWithoutAdditionalText(){
        //given
        String testGeneralText = "This is Default General Text";
        //when
        //then
    }

    @Test
    public void applicationGetsAllDataFromDatabase(){
        //given
        //when
        //then
    }

    @Test
    public void applicationDisplaysTextFromDatabase(){
        //given
        //when
        //then
    }

    @Test
    public void applicationDrawsFromData(){
        //given
        //when
        //then
    }

    @Test
    public void applicationSelectsOnlyDataWithAppropriateTagToCursor(){
        //given
        //when
        //then
    }

    @Test
    public void applicationChecksDayFromCurrentDate(){
        //given
        //when
        //then
    }

    @Test
    public void applicationFitsDayWithTag(){
        //given
        //when
        //then
    }

    @Test
    public void applicationDisplaysTextForDay(){
        //given
        //when
        //then
    }

    @Test
    public void applicationDisplaysTextForCurrentDate(){
        //given
        //when
        //then
    }


    @Test
    public void applicationAddsToDatabaseDateWhenMessageWasDisplayed(){
        //given
        //when
        //then
    }

    @Test
    public void applicationGetsDataWithNoDateOnly(){
        //given
        //when
        //then
    }

    @Test
    public void applicationGetsDataWithDateSinceDate(){
        //given
        //when
        //then
    }

    @Test
    public void applicationDrawsDataWithNoDateOnly(){
        //given
        //when
        //then
    }

    @Test
    public void applicationDrawsDataWithDateSinceDateWhenAllDataWasUsed(){
        //given
        //when
        //then
    }

    @Test
    public void test(){
        //given
        //when
        //then
    }

}
