package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static int extractInteger(String text){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return -1;
    }
}
