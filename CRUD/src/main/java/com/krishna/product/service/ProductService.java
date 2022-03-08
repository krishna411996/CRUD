package com.krishna.product.service;


import com.krishna.product.exception.ResourceNotFoundException;
import com.krishna.product.model.Category;
import com.krishna.product.model.Product;
import com.krishna.product.model.User;
import com.krishna.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          CategoryService categoryService,
                          UserService userService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> getAllProduct(String username) {
        User user = userService.getUserByUsername(username);
        List<Product> products = productRepository.findByUser(user);

        return products;
    }

    public Product saveProduct(Product product, String username) {
        Category category = categoryService.getCategoryByName(product.getCategory().getCategoryName());
        product.setCategory(category);
        if (username != null) {
            User user = userService.getUserByUsername(username);
            product.setUser(user);
        }
        productRepository.save(product);

        return product;
    }

    public Product updateProduct(Integer id, Product updateProduct, String username) {
        Product product = getProductById(id);
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setQuantity(updateProduct.getQuantity());
        product.setCategory(updateProduct.getCategory());
        Product savedProduct = saveProduct(product, username);

        return savedProduct;
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);

    }

    public Product getProductById(Integer id) {
        //Product product = productRepository.findOne(id);
        Product product = productRepository.findById(id).orElse(null);


        /*if (product == null){
            throw new ResourceNotFoundException("Product not found.");
        }*/

        return product;
    }
}
