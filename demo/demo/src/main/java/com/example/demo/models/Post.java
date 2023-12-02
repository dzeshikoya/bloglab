package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text, announce, title;

    public Long GetId() { // получить id
        return id;
    }

    public String GetTitle() { // получить титульник какой-то и потом чтобы менять
        return title;
    }

    public void SetTitle(String title) {
        this.title = title;
    }

    public String GetAnnounce() { // получить анонс какой-то и потом чтобы менять
        return announce;
    }

    public void SetAnnounce(String announce) {
        this.announce = announce;
    }

    public String GetText() { // получить текст и потом чтобы менять
        return text;
    }

    public void SetText(String text) {
        this.text = text;
    }

    public Post() {

    }

    public Post(String text, String announce, String title) {

        this.title = title;
        this.announce = announce;
        this.text = text;

    }

}
