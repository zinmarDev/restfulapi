package com.example.restfulapi.model;


public class Sushi {
    int id;
    String name;
    String img_url;
    String created_at;
    float price;

    public Sushi(){

    }

    public Sushi(int id, String name, String img_url, String created_at, float price){
        super();
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.price = price;
        this.created_at = created_at;

    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
