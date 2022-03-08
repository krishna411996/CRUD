package com.krishna.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.product.model.Product;
import com.krishna.product.model.User;

import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Integer> {
        List<Product> findByUser(User user);


}

