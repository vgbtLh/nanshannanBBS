package com.example.myapplication;

public class DrawerItem {
    private int imgId;
    private String content;

    public DrawerItem() {}

    public DrawerItem(String content) {
        this.content = content;
    }

 /*   public int getImgId() {
        return this.imgId;
    }*/

    public String getContent() {
        return this.content;
    }

    /*public void setImgId(int imgId) {
        this.imgId = imgId;
    }*/

    public void setContent(String content) {
        this.content = content;
    }
}
