package edu.tacoma.uw.set.css.uwleonappbr.news.model;


import java.io.Serializable;

public class Article implements Serializable {

    private String name, image, date, link;

    public final static String NAME = "name";
    public final static String IMAGE = "image";
    public final static String DATE = "date";
    public final static String LINK = "link";



    public Article( String name, String image, String date, String link) {
        this.name = name;
        this.date = date;
        this.link = link;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
