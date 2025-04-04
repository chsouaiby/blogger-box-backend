package com.dauphine.blogger.dtos;

import com.dauphine.blogger.services.PostService;
import java.util.UUID;

public class CreationPostRequest {
    private String title;
    private String content;
    private UUID categoryId;

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

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
