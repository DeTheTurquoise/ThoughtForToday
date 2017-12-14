package dmm.kobietapewnasiebie.database;

import android.provider.BaseColumns;

/**
 * Created by ddabrowa on 2017-09-29.
 */

public class KPScontract {
    private KPScontract(){}

    public static final class HelloMessages implements BaseColumns{
        public static final String TABLE_NAME = "helloMessages";
        public static final String COLUMN_HELLO_TEXT = "helloText";
        public static final String COLUMN_ADDITIONAL_TEXT = "additionalText";
        public static final String COLUMN_TEXT_TYPE = "textType";
        public static final String COLUMN_WHEN_USED = "whenUsed";
    }

}
