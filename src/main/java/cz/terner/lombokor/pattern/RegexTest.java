
package cz.terner.lombokor.pattern;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        String string = "+  ABCDEFG+ MKJHFRT+_01215451544+1234567";
        //String[] pole = string.split("\\+");
        //System.out.println(Arrays.asList(pole));
        String regex = "(?=[A-Z]{7})[A-Z]{4}([\\w]{3})[.]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
