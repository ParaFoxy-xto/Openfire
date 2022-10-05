package com.example.openfire.demo.model;

public class Chatter {

    public Chatter(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String username;

    private String email;

    private String name;



    @Override
    public String toString() {
        return "Data{" +
                "Username='" + username + '\'' +
                "Name='" + name + '\'' +
                ", Email=" + email +
                '}';
    }


    public Chatter save(Chatter chatter) {
        return null;

    }
}
