package com.example.myapplication;

public class HomeRecentNews {
    private String title;
    int[] date = new int[3];

    public HomeRecentNews() {}

    public HomeRecentNews(String title, int[] date) {
        this.title = title;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDate(int[] date) {
        this.date = date;
    }

    public int[] getDate() {
        return this.date;
    }
}
