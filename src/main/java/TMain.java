
import wallhow.dreamlo.sdk.DreamloSDK;
import wallhow.dreamlo.sdk.achievements.Achievement;

/**
 * Created by wallhow on 23.02.17.
 */
public class TMain {
    public static void main(String[] arg) {
        DreamloSDK dreamloSDK = new DreamloSDK("",
                "");

        dreamloSDK.getAchievemts().set("lol", true);
        dreamloSDK.getAchievemts().set("lol3", false);
        dreamloSDK.getAchievemts().set("Job", true);

        for (Achievement achievement : dreamloSDK.getAchievemts().list()) {
            System.out.println(achievement);
        }
    }
}
