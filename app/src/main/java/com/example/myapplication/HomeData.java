package com.example.myapplication;

public class HomeData {
    private int imgId;
    private String content;
    private String title;
    private String readQuantity;

    public HomeData() {}

    public HomeData(int imgId, String content, String title, String readQuantity) {
        this.imgId = imgId;
        this.content = content;
        this.title = title;
        this.readQuantity = readQuantity;
    }

    public int getImgId() {
        return this.imgId;
    }

    public String getContent() {
        return this.content;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle() {
        this.title = title;
    }

    public String getReadQuantity() {
        return this.readQuantity;
    }

    public void setReadQuantity(String readQuantity) {
        this.readQuantity = readQuantity;
    }
}
