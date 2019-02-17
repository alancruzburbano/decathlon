package com.kuenag.app.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class TextVerifiable {

    public static Predicate<String> isValidPath = text -> Files.exists(Paths.get(text));

    public static Predicate<String> isValidName =  text -> text.matches(Constants.REGEX_NAME_FORMAT);

    public static Predicate<String> isValidDecimal =  text -> text.trim().matches(Constants.REGEX_DECIMAL_FORMAT);

    public static Predicate<String> isValidTime = text -> text.trim().matches(Constants.REGEX_TIME_FORMAT);

}
