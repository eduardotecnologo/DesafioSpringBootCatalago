package com.edudeveloper.restaurantecatalago.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.edudeveloper.restaurantecatalago.domain.category.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>{
    
}
