package com.springproject.ECommerceSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, unique = true, nullable = false)
	private ERole name;
	 public Role() {}

	    public Role(ERole name) {
	        this.name = name;
	    }

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public ERole getName() {
	        return name;
	    }

	    public void setName(ERole name) {
	        this.name = name;
	    }
	
}
