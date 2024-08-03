package com.edudeveloper.restaurantecatalago.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edudeveloper.restaurantecatalago.domain.category.Category;
import com.edudeveloper.restaurantecatalago.domain.category.CategoryDTO;
import com.edudeveloper.restaurantecatalago.domain.exceptions.CategoryNotFoundException;
import com.edudeveloper.restaurantecatalago.repository.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository repository;
    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public Category insert(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }

    public Category update(String id,CategoryDTO categoryData){
        Category category = this.repository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());
        this.repository.save(category);
        return category;
    }

    public void delete(String id){
        Category category = this.repository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(id);
    }
}
