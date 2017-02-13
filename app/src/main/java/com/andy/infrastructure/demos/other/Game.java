package com.andy.infrastructure.demos.other;

/**
 * Created by Administrator on 2016/1/13.
 */
public class Game {
    String tag;
    String price;

    public Game(String tag, String price) {
        this.tag = tag;
        this.price = price;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
