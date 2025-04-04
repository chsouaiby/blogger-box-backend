package com.dauphine.blogger.models;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructors
    public Post() {
    }

    public Post(UUID id, String title, String content, UUID categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.category = new Category(categoryId);
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public UUID getCategoryId() {
        return category.getId();
    }

    public void setCategoryId(UUID categoryId) {
        (this.category).setId(categoryId);
    }
}
