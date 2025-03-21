package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.models.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Category Controller")
public class CategoryController {

    private final List<Category> categories = new ArrayList<>();

    @GetMapping("v1/categories")
    @Operation(summary = "Get all categories", description = "Returns all the existent categories.")
    public List<Category> getAll() {
        return categories;
    }

    @GetMapping("v1/categories/{id}")
    @Operation(summary = "Get category by id (path variable)", description = "Returns a category by its id given in the path")
    public Category getById(@Parameter(description = "id of category to retrieve") @PathVariable UUID id) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("v1/categories")
    @Operation(summary = "Create category", description = "Creates a category based on the content request in the parameters")
    public Category create(@Parameter(description = "request of the category content") @RequestBody CreationCategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setId(UUID.randomUUID());
        categories.add(category);
        return category;
    }

    @PutMapping("v1/categories/{id}")
    @Operation(summary = "Update category name", description = "Updates the name of a category by its id")
    public Category update(@Parameter(description = "id of category to update") @PathVariable UUID id,
                           @Parameter(description = "new name of the category") @RequestBody CreationCategoryRequest categoryRequest) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(category -> {
                    category.setName(categoryRequest.getName());
                    return category;
                })
                .orElse(null);
    }

    @DeleteMapping("v1/categories/{id}")
    @Operation(summary = "Delete category", description = "Deletes an existing category by its id")
    public void delete(@Parameter(description = "id of category to delete") @PathVariable UUID id) {
        categories.removeIf(c -> c.getId().equals(id));
    }
}