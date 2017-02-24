
import wallhow.dreamlo.sdk.DreamloSDK;
import wallhow.dreamlo.sdk.achievements.Achievement;

import java.lang.reflect.Array;

/**
 * Created by wallhow on 23.02.17.
 */
public class TMain {
    public static void main(String[] arg) {
        DreamloSDK dreamloSDK = new DreamloSDK("",
                "");


        Achievement[] arr = dreamloSDK.getAchievemts().list();
        for (Achievement achievement : arr) {
            System.out.println(achievement);
        }
    }
}
