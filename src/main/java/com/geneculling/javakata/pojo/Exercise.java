package com.geneculling.javakata.pojo;

import java.util.Date;

public class Exercise {
    private String id;
    private String description;
    private String duration;
    private Date date;

    public Exercise(String id, String description, String duration) {
        this(id, description, duration, new Date());
    }

   public Exercise(String id, String description, String duration, Date date) {
        this.id = id;
        this.description = description;
        this.duration = duration;
        this.date = date;
    }
}
