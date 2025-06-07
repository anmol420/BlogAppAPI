package com.anmol420.blog_app.services;

import com.anmol420.blog_app.domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryServices {
    List<Category> getCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID id);
}
