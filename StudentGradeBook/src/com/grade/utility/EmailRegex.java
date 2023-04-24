package com.grade.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRegex {
    private static String emailRegex = "^(.+)@(.+).(.+)$";

    private static Pattern pattern = Pattern.compile(emailRegex);

    public static boolean emailCheck(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    };

}