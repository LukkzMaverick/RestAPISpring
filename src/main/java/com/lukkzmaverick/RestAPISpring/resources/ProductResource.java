package com.lukkzmaverick.RestAPISpring.resources;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lukkzmaverick.RestAPISpring.models.Product;
import com.lukkzmaverick.RestAPISpring.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST - Model Product")
@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}
	
	@ApiOperation(value = "Find All Products in Database")
	@GetMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> findAll(){
		List<Product> list = this.productService.findAll();
		return ResponseEntity.ok(list);
				
	}
	
	@ApiOperation(value = "Find By Id in Database")
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<Product> product = this.productService.findById(id);
		return ResponseEntity.of(product);
	}
	
	@ApiOperation(value = "Create a new Product")
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
		if(!errors.hasErrors()) {
			Product productCreated = this.productService.create(product);
			return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
		}
		
		String body = errors.getAllErrors().stream().map(msg -> msg.getDefaultMessage()).collect(Collectors.joining(","));
		
		return ResponseEntity.badRequest().body(body);
	}
	
	@ApiOperation(value = "Update a Product by id")
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @RequestBody Product product, Errors errors) {
		if(!errors.hasErrors()) {
			Product productUpdated = this.productService.update(id, product);
			return new ResponseEntity<Product>(productUpdated, HttpStatus.ACCEPTED);
		}
		
		String body = errors.getAllErrors().stream().map(msg -> msg.getDefaultMessage()).collect(Collectors.joining(","));
		
		return ResponseEntity.badRequest().body(body);
	}
	
	@ApiOperation(value = "Delete product by Id")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable("id") Long id, HttpServletResponse response) {
		boolean deletedWithSuccess = this.productService.delete(id);
		if(!deletedWithSuccess) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
}
