package com.yzp.spring.springbootsamples.exp.es_jd.model;

public class Content {
    private String title;
    private String img;
    private String price;

    public Content(String title, String img, String price) {
        this.title = title;
        this.img = img;
        this.price = price;
    }

    public Content() {
    }

    public String getTitle() {
        return title;
    }

    public Content setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImg() {
        return img;
    }

    public Content setImg(String img) {
        this.img = img;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Content setPrice(String price) {
        this.price = price;
        return this;
    }
}
