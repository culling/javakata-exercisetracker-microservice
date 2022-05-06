package com.geneculling.javakata.pojo;

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
    private String _id;
    private String description;
    private String duration;
    private Date date;
    private String username;

    public Exercise(String _id, String username, String description, String duration) {
        this(_id, username, description, duration, new Date());
    }

    public Exercise(String _id, String username, String description, String duration, Date date) {
        this.username = username;
        this._id = _id;
        this.description = description;
        this.duration = duration;
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

    public Date getDate() {
        return date;
    }
}
