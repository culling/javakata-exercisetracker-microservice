package com.geneculling.javakata.pojo;

import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * JSON for object
 * {
 *     username: "fcc_test",
 *     description: "test",
 *     duration: 60,
 *     date: "Mon Jan 01 1990",
 *     _id: "5fb5853f734231456ccb3b05"
 * }
 */
public class Exercise {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy MM dd");

    private String _id;
    private String description;
    private String duration;
    private String date;
    private String username;



    public Exercise(String _id, String username, String description, String duration, String date) {
        this.username = username;
        this._id = _id;
        this.description = description;
        this.duration = duration;
        if(StringUtils.isBlank(date)){
            date = DATE_FORMAT.format(new Date());
        }
        this.date = date;
    }

    public String getId() {
        return _id;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }
}
