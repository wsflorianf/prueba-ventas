package com.prueba.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.backend.dtos.NuevaVentaDto;
import com.prueba.backend.models.Venta;
import com.prueba.backend.services.VentaService;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Obtener todas las ventas
    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    // Obtener una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Venta venta = ventaService.getVentaById(id);

        return ResponseEntity.ok(venta);
    }

    // Crear una nueva venta
    @PostMapping
    public ResponseEntity<?> createVenta(@RequestBody NuevaVentaDto venta) {
    	Venta resultado = ventaService.createVenta(venta);
        return ResponseEntity.ok(resultado);
    }

    // Actualizar una venta existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Long id, @RequestBody NuevaVentaDto actVenta) {
    	Venta venta = ventaService.updateVenta(id, actVenta);
        return ResponseEntity.ok(venta);
    }

    // Eliminar una venta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
    	ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}