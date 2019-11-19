package com.avantica.proa.Models;

import javax.persistence.*;

@Entity
@Table(name = "fbuser")
public class FBUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "fbtoken")
    private String fbtoken;

    public FBUser() {
    }

    public FBUser(String email, String name, String fbtoken) {
        this.email = email;
        this.name = name;
        this.fbtoken = fbtoken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFbtoken() {
        return fbtoken;
    }

    public void setFbtoken(String fbtoken) {
        this.fbtoken = fbtoken;
    }
}
