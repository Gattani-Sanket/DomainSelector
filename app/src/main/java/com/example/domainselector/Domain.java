package com.example.domainselector;

public class Domain {

    private String Title;
    private int thumbnail;

    public Domain(String title, int thumbnail) {
        Title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return Title;
    }


    public int getThumbnail() {
        return thumbnail;
    }

}
