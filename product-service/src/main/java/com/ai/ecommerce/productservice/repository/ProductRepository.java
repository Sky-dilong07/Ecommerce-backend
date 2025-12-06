package com.ai.ecommerce.productservice.repository;

import com.ai.ecommerce.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
