package com.example.myapplication;

public class LuntanItem {
    private int headPortraitId;
    private String userName;
    private String contentText;
    private int readNumber;
    private int commentNumber;

    public LuntanItem() {}

    public LuntanItem(int headPortraitId, String userName, String contentText, int readNumber,
                           int commentNumber) {
        this.headPortraitId = headPortraitId;
        this.userName = userName;
        this.contentText = contentText;
        this.readNumber = readNumber;
        this.commentNumber = commentNumber;
    }

    public void setHeadPortraitId(int headPortraitId) {
        this.headPortraitId = headPortraitId;
    }

    public int getHeadPortraitId() {
        return this.headPortraitId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public  void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentText() {
        return this.contentText;
    }

    public void setReadNumber(int readNumber) {
        this.readNumber = readNumber;
    }

    public int getReadNumber() {
        return this.readNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getCommentNumber() {
        return this.commentNumber;
    }
}
