package com.lukkzmaverick.RestAPISpring.services;

import java.util.List;
import java.util.Optional;

import com.lukkzmaverick.RestAPISpring.models.Product;

public interface ProductService {
	public List<Product> findAll();
	public Optional<Product> findById(Long id);
	public Product create(Product product);
	public Product update(Long id, Product product);
	public Boolean delete(Long id);
}
