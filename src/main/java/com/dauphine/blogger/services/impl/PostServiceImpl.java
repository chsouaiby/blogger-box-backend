package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    @Override
    public List<Post> getByCategory(UUID categoryId) {
        return repository.findAll().stream()
                .filter(post -> categoryId.equals(post.getCategoryId()))
                .sorted((p1, p2) -> p2.getCreatedDate().compareTo(p1.getCreatedDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Post getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, categoryId);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        return repository.findById(id)
                .map(post -> {
                    post.setTitle(title);
                    post.setContent(content);
                    return repository.save(post);
                })
                .orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}