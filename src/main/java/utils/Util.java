package utils;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class Util {

    public static boolean isSet(Object o) {
        return o != null;
    }

    public static void log(String... messages) {
        StringBuilder builder = new StringBuilder("|");
        for (String message : messages) {
            builder.append(message).append("|");
        }
        System.out.println(builder.toString());
    }

}
