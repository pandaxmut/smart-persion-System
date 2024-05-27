package com.edu.smartpersionsys.pojo;

import lombok.Data;

@Data
public class Suggestions {
    private Integer id;
    private Integer olderId;
    private String olderName;
    private String title;
    private String description;
    private String date;
    private String olderPhoto;


    public Suggestions() {
    }

    public Suggestions(Integer id, Integer olderId, String olderName, String title, String description, String date, String olderPhoto) {
        this.id = id;
        this.olderId = olderId;
        this.olderName = olderName;
        this.title = title;
        this.description = description;
        this.date = date;
        this.olderPhoto = olderPhoto;
    }
}
