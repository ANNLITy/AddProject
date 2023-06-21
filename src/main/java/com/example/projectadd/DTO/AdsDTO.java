package com.example.projectadd.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AdsDTO {
    private long author;
    private String image;
    private long pk;
    private int price;
    private String title;
    private String description;

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

