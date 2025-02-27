package com.prueba.backend.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NuevaVentaDto {
	private Long id;
    private Long vendedorId;
    private Long productoId;
    private int cantidad;
    private LocalDate fecha;
}