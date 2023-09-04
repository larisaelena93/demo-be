package com.example.modelbe.dao;

import com.example.modelbe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "productCategories", path = "productCategories")
public interface ProductCategoryRepository extends JpaRepository<Product, Long> {
}
