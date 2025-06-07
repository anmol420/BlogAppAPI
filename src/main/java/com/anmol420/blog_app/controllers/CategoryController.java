package com.anmol420.blog_app.controllers;

import com.anmol420.blog_app.domain.dtos.CategoryDTO;
import com.anmol420.blog_app.domain.dtos.CreateCategoryRequest;
import com.anmol420.blog_app.domain.entities.Category;
import com.anmol420.blog_app.mappers.CategoryMapper;
import com.anmol420.blog_app.services.CategoryServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServices categoryServices;
    private final CategoryMapper categoryMapper;

    @GetMapping(path = "/getCategories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categories = categoryServices.getCategories()
                .stream().map(categoryMapper::toDTO)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping(path = "/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
    ) {
        Category categoryToCreate = categoryMapper.fromDTO(createCategoryRequest);
        Category savedCategory = categoryServices.createCategory(categoryToCreate);
        return new ResponseEntity<>(
                categoryMapper.toDTO(savedCategory),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/deleteCategory/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable UUID id
    ) {
        categoryServices.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
