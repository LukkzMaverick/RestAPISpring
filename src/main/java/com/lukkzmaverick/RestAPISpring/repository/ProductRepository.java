package com.lukkzmaverick.RestAPISpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lukkzmaverick.RestAPISpring.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
