package com.edudeveloper.restaurantecatalago.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edudeveloper.restaurantecatalago.domain.category.Category;
import com.edudeveloper.restaurantecatalago.domain.category.CategoryDTO;
import com.edudeveloper.restaurantecatalago.services.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/products")
public class ProductContoller {
     private ProductService service;

    public ProductContoller(ProductService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData){
        Category newCategory = this.service.insert(categoryData);
        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>>getAll() {
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category>update(@PathParam("id") String id, @RequestBody CategoryDTO categoryData) {
        Category updatedCategory = this.service.update(id, categoryData);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category>delete(@PathParam("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
