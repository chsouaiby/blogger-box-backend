package com.dauphine.blogger.models;

import java.util.UUID;

public class Category {

    private UUID id;
    private String name;

    //Constructors
    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
        this.id = null;
        this.name = null;
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
