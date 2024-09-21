package org.coding.practice.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPatterReplacer {

    private static Map<String, String> dict = new HashMap<>();

    public static void main(String[] args)
    {
        populateDict();
        List<String> regexList = new ArrayList<>();
        String str = "home/usr/lib/%EXAMPLE%%USER%";
        Pattern pattern = Pattern.compile("%[a-zA-Z]+%");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find())
        {
            regexList.add(matcher.group());
        }
        String output = str;
        for(String regex : regexList)
        {
            output = replaceRegexInString(regex.replaceAll("%", ""), regex, output);
        }
        System.out.println(output);
    }

    private static String replaceRegexInString(String enclosedWord, String regex, String input)
    {
        return input.replaceAll(regex, dict.get(enclosedWord));
    }

    private static void populateDict()
    {
        dict.put("DATE","01/01/2024");
        dict.put("USER", "John");
        dict.put("EXAMPLE", "testfile.txt");
    }
}
