package com.krishna.product.controller;


import java.util.List;

import com.krishna.product.model.Product;
import com.krishna.product.service.ProductService;
import com.krishna.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public ResponseEntity<List<ProductService>> getAllProduct(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<ProductService> list = productService.getAllProduct(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<ProductService>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //List<ProductService> list = productService.getAllEmployees(pageNo, pageSize, sortBy);

    @GetMapping(path = "/products", produces = "application/json")
    public List<Product> getAllProduct(Authentication authentication) {
        String username = authentication.getName();
        List<Product> productList = productService.getAllProduct(username);

        return productList;
    }

    @GetMapping(path = "/products/{id}")
    public Product getProduct(@PathVariable Integer id) {
        Product product = productService.getProductById(id);

        return product;
    }


    @GetMapping
    public Page<Product> getProductList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        return productService.findAll(paging);
    }
    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product newProduct, Authentication authentication) {
        String username = authentication.getName();

        return productService.saveProduct(newProduct, username);
    }

    @DeleteMapping(path = "/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody Product updateProduct, Authentication authentication) {
        Product product = productService.getProductById(id);
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setQuantity(updateProduct.getQuantity());
        product.setCategory(updateProduct.getCategory());

        String username = authentication.getName();
        productService.saveProduct(product, username);
    }
}
