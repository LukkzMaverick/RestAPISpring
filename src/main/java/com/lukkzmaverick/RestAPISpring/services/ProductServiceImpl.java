package com.lukkzmaverick.RestAPISpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lukkzmaverick.RestAPISpring.models.Product;
import com.lukkzmaverick.RestAPISpring.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return this.productRepository.findById(id);
	}

	@Override
	public Product create(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Product update(Long id, Product product) {
		Product productExists = this.productRepository.findById(id).orElse(null);
		if(productExists != null) {
			product.setId(productExists.getId());
			this.productRepository.save(product);
			return product;
		}else {
			System.out.println("Teste");
			return null;
		}
	}

	@Override
	public Boolean delete(Long id) {
		try {
			this.productRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
}
