package com.geneculling.javakata.pojo;

import java.util.Date;

public class Exercise {

    private String description;
    private String duration;
    private Date date;

    public Exercise(String description, String duration) {
        new Exercise(description, duration, new Date());
    }

   public Exercise(String description, String duration, Date date) {
        this.description = description;
        this.duration = duration;
        this.date = date;
    }
}
