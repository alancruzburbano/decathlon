package com.kuenag.app.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class TextVerifiable {

    public static Predicate<String> isValidPath = text -> (text!=null) && (Files.exists(Paths.get(text)));

    public static Predicate<String> isValidName =  text -> (text!=null) &&  text.matches(Constants.REGEX_NAME_FORMAT);

    public static Predicate<String> isValidDecimal =  text -> (text!=null) && text.trim().matches(Constants.REGEX_DECIMAL_FORMAT);

    public static Predicate<String> isValidTime = text -> (text!=null) && text.trim().matches(Constants.REGEX_TIME_FORMAT);

}
