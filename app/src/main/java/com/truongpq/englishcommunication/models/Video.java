package com.truongpq.englishcommunication.models;

/**
 * Created by TruongPQ on 5/6/16.
 */
public class Video {
    private String id;
    private String title;
    private String decription;

    public Video() {
    }

    public Video(String id, String title, String decription) {
        this.id = id;
        this.title = title;
        this.decription = decription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
