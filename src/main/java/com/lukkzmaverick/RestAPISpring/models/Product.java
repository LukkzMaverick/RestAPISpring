package com.lukkzmaverick.RestAPISpring.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	@NotEmpty(message = "Cannot be empty")
	@NotBlank(message = "Cannot be blank")
	@Size(min = 4, max = 255)
	private String name;
	
	@Column
	@Min(0)
	@Max(1000)
	private int amount;
	
	@Column
	private Date dateCreated;
	
	public Product() {
		
	}
	
	public Product(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	@PrePersist
	public void onPrePersist() {
		if(this.dateCreated == null) {
			this.dateCreated = new Date();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public String toString() {
		return String.format("{id: %d, name: %s, amount %d}",this.id, this.name, this.amount );
	}
	
}
