package com.geneculling.javakata.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;

public class ExerciseValidator {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy MM dd", Locale.ENGLISH);

    public static boolean isValidDate(String date){
        if(StringUtils.isEmpty(date)){
            return true;
        }
        try {
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException dateTimeParseException){
            return false;
        }
        return true;
    }

    public static boolean isValidDuration(String duration){
        if(StringUtils.isEmpty(duration)){
            return false;
        }
        if(!Pattern.matches("\\d*", duration)){
            return false;
        }
        return true;
    }
}
