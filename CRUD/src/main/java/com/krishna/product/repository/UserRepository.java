package com.krishna.product.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.product.model.User;
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

