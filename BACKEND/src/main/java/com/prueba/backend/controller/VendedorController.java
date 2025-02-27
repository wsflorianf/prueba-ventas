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

import com.prueba.backend.models.Vendedor;
import com.prueba.backend.services.VendedorService;

@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    // Obtener todos los vendedores
    @GetMapping
    public List<Vendedor> getAllVendedores() {
        return vendedorService.getAllVendedores();
    }

    // Obtener un vendedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long id) {
        Vendedor vendedor = vendedorService.getVendedorById(id);

        return ResponseEntity.ok(vendedor);
    }

    // Crear un nuevo vendedor
    @PostMapping
    public ResponseEntity<Vendedor> createVendedor(@RequestBody Vendedor vendedor) {
    	Vendedor resultado = vendedorService.createVendedor(vendedor);
        return ResponseEntity.ok(resultado);
    }

    // Actualizar un vendedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> updateVendedor(@PathVariable Long id, @RequestBody Vendedor actVendedor) {
    	Vendedor updatedVendedor = vendedorService.updateVendedor(id, actVendedor);
        return ResponseEntity.ok(updatedVendedor);
    }

    // Eliminar un vendedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
    	vendedorService.deleteVendedor(id);
        return ResponseEntity.noContent().build();
    }
}