package com.krishna.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.product.model.Product;
import com.krishna.product.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>, CrudRepository<Product, Integer> {
        List<Product> findByUser(User user);

}

//extends JpaRepository<Product, Integer>,