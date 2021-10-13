package ru.tuanviet.javabox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)

public class NewsModel {
    private String title;
    private String url;
    private String score;
    private String id;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID " + getId() + " Title " + getTitle() + " Url " + getUrl() + " score " + getScore();
    }
}
