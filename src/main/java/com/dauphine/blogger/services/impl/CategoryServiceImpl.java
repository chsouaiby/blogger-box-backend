package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;
import com.dauphine.blogger.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository; // 6 usages

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override // 1 usage
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override // 1 usage
    public List<Category> getAllLikeName(String name) {
        return repository.findAllLikeName(name);
    }

    @Override // 2 usages
    public Category getById(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return repository.save(category);
    }

    @Override
    public Category updateName(UUID id, String name) {
        Category category = getById(id);
        if (category == null) {
            return null;
        }
        category.setName(name);
        return repository.save(category);
    }

    @Override // 1 usage
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }
}
