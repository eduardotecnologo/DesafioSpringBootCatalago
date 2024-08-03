package com.edudeveloper.restaurantecatalago.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edudeveloper.restaurantecatalago.domain.category.Category;
import com.edudeveloper.restaurantecatalago.domain.category.CategoryDTO;
import com.edudeveloper.restaurantecatalago.domain.exceptions.CategoryNotFoundException;
import com.edudeveloper.restaurantecatalago.domain.product.Product;
import com.edudeveloper.restaurantecatalago.domain.product.ProductDTO;
import com.edudeveloper.restaurantecatalago.repository.CategoryRepository;
import com.edudeveloper.restaurantecatalago.repository.ProductRepository;

@Service
public class ProductService {
    private CategoryService categoryService;
    private ProductRepository repository;
    
    public ProductService(CategoryService categoryService,ProductRepository productRepository){
        this.categoryService = categoryService;
        this.repository = productRepository;
    }

    public Product insert(ProductDTO productData){
        
        Product newProduct = new Product(productData);
        this.repository.save(newProduct);
        return newProduct;
    }

    public List<Product>getAll(){
        return this.repository.findAll();
    }

    public Product update(String id,ProductDTO productData){
        Product product = this.repository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        this.repository.save(product);
        return product;
    }

    public void delete(String id){
        Product product = this.repository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(product);
    }
}
