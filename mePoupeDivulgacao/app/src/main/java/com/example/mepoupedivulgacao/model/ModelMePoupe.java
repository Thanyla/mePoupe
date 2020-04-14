package com.example.mepoupedivulgacao.model;

public class ModelMePoupe {
    private String id;
    private String title;
    private String height;
    private String url;
    private String width;


    public ModelMePoupe(String id, String title, String height, String url, String width) {
        this.id = id;
        this.title = title;
        this.height = height;
        this.url = url;
        this.width = width;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
