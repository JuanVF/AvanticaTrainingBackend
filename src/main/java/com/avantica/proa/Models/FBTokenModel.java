package com.avantica.proa.Models;

public class FBTokenModel {
    private String name;
    private long id;

    public FBTokenModel() {
    }

    public FBTokenModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
