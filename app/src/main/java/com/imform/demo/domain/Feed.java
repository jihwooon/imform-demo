package com.imform.demo.domain;

public class Feed {

    private Long id;
    private String title;
    private String url;

    public Feed(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
