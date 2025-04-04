package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dtos.CreationPostRequest;
import com.dauphine.blogger.dtos.UpdatePostRequest;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Post Controller", description = "Manage blog posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("v1/posts")
    @Operation(summary = "Create post", description = "Creates a new post based on the request parameters")
    public Post create(
            @Parameter(description = "Post creation request containing title, content, and categoryId")
            @RequestBody CreationPostRequest postRequest) {
        return postService.create(postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
    }

    @PutMapping("v1/posts/{id}")
    @Operation(summary = "Update post", description = "Updates an existing post by its ID")
    public Post update(
            @Parameter(description = "ID of the post to update", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Updated post request containing new title and content")
            @RequestBody UpdatePostRequest postRequest) {
        return postService.update(id, postRequest.getTitle(), postRequest.getContent());
    }

    @DeleteMapping("v1/posts/{id}")
    @Operation(summary = "Delete post", description = "Deletes an existing post by its ID")
    public void delete(
            @Parameter(description = "ID of the post to delete", required = true)
            @PathVariable UUID id) {
        postService.deleteById(id);
    }

    @GetMapping("v1/posts")
    @Operation(summary = "Get all posts", description = "Returns all posts ordered by creation date")
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("v1/posts/category/{categoryId}")
    @Operation(summary = "Get posts by category", description = "Returns all posts for a specific category")
    public List<Post> getByCategory(
            @Parameter(description = "ID of the category to filter posts", required = true)
            @PathVariable UUID categoryId) {
        return postService.getByCategory(categoryId);
    }
}
