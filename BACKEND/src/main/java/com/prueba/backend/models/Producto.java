package com.prueba.backend.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "productos")
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
