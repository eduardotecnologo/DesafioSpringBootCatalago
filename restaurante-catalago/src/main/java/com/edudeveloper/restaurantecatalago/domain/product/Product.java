package com.edudeveloper.restaurantecatalago.domain.product;

import java.util.Locale.Category;

import org.springframework.data.mongodb.core.mapping.Document;

import com.edudeveloper.restaurantecatalago.domain.category.CategoryDTO;
import com.edudeveloper.restaurantecatalago.domain.category.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private Category category;

    public Product(ProductDTO data){
        this.title = data.title();
        this.description = data.description();
        this.ownerId = data.ownerId();
        this.price = data.price();
    }
}
