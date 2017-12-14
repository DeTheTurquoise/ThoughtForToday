package dmm.kobietapewnasiebie.utils;

/**
 * Created by ddabrowa on 2017-10-02.
 */

public class Tags {
    public final static String[] HelloMessagesTags = {
            "quote", "thought", "affirmation", "question"
    };

    public static String getTag(int dayName){
        if(dayName==6){
            dayName++;
        } else if(dayName==7){
            dayName--;
        }
        int dayParameter = (dayName + 3)%4;
        return Tags.HelloMessagesTags[dayParameter];
    }
}
