package com.krishna.product.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.product.model.Category;
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findByCategoryName(String name);
}
