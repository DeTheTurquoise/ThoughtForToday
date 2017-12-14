package dmm.kobietapewnasiebie;

import android.provider.BaseColumns;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import dmm.kobietapewnasiebie.database.KPScontract;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ddabrowa on 2017-10-02.
 */

public class ContractTest {
    @Test
    public void contractHasOneInnerClasses() throws Exception {
        Class[] innerClasses = KPScontract.class.getDeclaredClasses();
        assertEquals(1,innerClasses.length);
    }

    @Test
    public void helloMessageClassFromContractIsAssignableFinalAndStatic() throws Exception{
        Class[] innerClasses = KPScontract.class.getClasses();
        Class activityClass = innerClasses[0];
        assertTrue(BaseColumns.class.isAssignableFrom(activityClass));
        assertTrue(Modifier.isFinal(activityClass.getModifiers()));
        assertTrue(Modifier.isFinal(activityClass.getModifiers()));
    }

    @Test
    public void helloMessageClassHasFourMembers() throws Exception{
        Class[] innerClass = KPScontract.class.getClasses();
        Class activityClass = innerClass[0];
        assertEquals(4,activityClass.getDeclaredFields().length);
    }

    @Test
    public void columnOfActivityClassAreStringFinalAndStatic() throws Exception {
        Class[] innerClass = KPScontract.class.getClasses();
        Class activityClass = innerClass[0];
        Field[] activityClassFields = activityClass.getDeclaredFields();
        for (Field field : activityClassFields) {
            assertTrue(field.getType() == String.class);
            assertTrue(Modifier.isFinal(field.getModifiers()));
            assertTrue(Modifier.isStatic(field.getModifiers()));
        }
    }
}
