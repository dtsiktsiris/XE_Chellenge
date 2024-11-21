package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static int extractInteger(String text){
        Pattern pattern = Pattern.compile("\\d+");
        return Integer.getInteger(pattern.matcher(text).group());
    }
}
