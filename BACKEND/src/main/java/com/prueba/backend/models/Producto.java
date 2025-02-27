package com.prueba.backend.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@Column(name = "nombre", nullable = false)
	public String nombre;
	
	@Column(name = "precio", nullable = false)
	public BigDecimal precio;
	
	@Column(name = "stock", nullable = false)
	public int stock;
}
