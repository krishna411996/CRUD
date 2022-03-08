package com.krishna.product.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.krishna.product.model.Category;
import com.krishna.product.repository.CategoryRepository;

import javax.persistence.Id;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

        @Autowired
        public CategoryService(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        public List<Category> getAllCategory() {
            return categoryRepository.findAll();
        }

        public Category getCategoryById(int id) {
            //return categoryRepository.findOne(id);
            return categoryRepository.findAllById(Collections.singleton(id)).get(id);

        }

        public Category getCategoryByName(String name) {
            return categoryRepository.findByCategoryName(name);
        }
}

