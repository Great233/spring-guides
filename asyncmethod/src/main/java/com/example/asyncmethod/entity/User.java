package com.example.asyncmethod.entity;

import org.springframework.stereotype.Component;

/**
 * @author Great
 */
public class User {

    private String name;

    private String blog;

    public User(String name, String blog) {
        this.name = name;
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
