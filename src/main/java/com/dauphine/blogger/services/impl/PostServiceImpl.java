package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> temporaryPosts;

    public PostServiceImpl() {
        temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post(UUID.randomUUID(), "First Post", "Content of first post", UUID.randomUUID()));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Second Post", "Content of second post", UUID.randomUUID()));
    }

    @Override
    public List<Post> getAll() {
        return temporaryPosts.stream()
                .sorted((p1, p2) -> p2.getCreatedDate().compareTo(p1.getCreatedDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, categoryId);
        temporaryPosts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String newTitle, String newContent) {
        Post post = temporaryPosts.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);

        if (post != null) {
            post.setTitle(newTitle);
            post.setContent(newContent);
        }

        return post;
    }

    @Override
    public void deleteById(UUID id) {
        temporaryPosts.removeIf(post -> id.equals(post.getId()));
    }

    @Override
    public List<Post> getByCategory(UUID categoryId) {
        return temporaryPosts.stream()
                .filter(post -> categoryId.equals(post.getCategoryId()))
                .sorted((p1, p2) -> p2.getCreatedDate().compareTo(p1.getCreatedDate()))
                .collect(Collectors.toList());
    }
}
