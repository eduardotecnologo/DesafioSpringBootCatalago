package com.edudeveloper.restaurantecatalago.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edudeveloper.restaurantecatalago.domain.category.Category;
import com.edudeveloper.restaurantecatalago.domain.exceptions.CategoryNotFoundException;
import com.edudeveloper.restaurantecatalago.domain.product.Product;
import com.edudeveloper.restaurantecatalago.domain.product.ProductDTO;
import com.edudeveloper.restaurantecatalago.domain.product.exceptions.ProductNotFoundException;
import com.edudeveloper.restaurantecatalago.repository.ProductRepository;

import lombok.Setter;

@Setter
@Service
public class ProductService {
    private CategoryService categoryService;
    private ProductRepository repository;
    
    public ProductService(CategoryService categoryService,ProductRepository productRepository){
        this.categoryService = categoryService;
        this.repository = productRepository;
    }

    public Product insert(ProductDTO productData){
        Category category = this.categoryService.getById(productData.categoryId())
            .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        return newProduct;
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.repository.findById(id)
            .orElseThrow(ProductNotFoundException::new);

            if(productData.categoryId() != null){
                this.categoryService.getById(productData.categoryId())
                .ifPresent(product::setCategory);
            }

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());
       
        this.repository.save(product);
        return product;
    }

    public void delete(String id){
        Product product = this.repository.findById(id)
            .orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }

    public List<Product>getAll(){
        return this.repository.findAll();
    }
}
