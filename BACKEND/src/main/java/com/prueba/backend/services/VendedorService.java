package com.prueba.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.backend.exceptions.BadRequestException;
import com.prueba.backend.exceptions.NotFoundException;
import com.prueba.backend.models.Vendedor;
import com.prueba.backend.repositories.VendedorRepository;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    // Obtener todos los vendedores
    public List<Vendedor> getAllVendedores() {
        return vendedorRepository.findAll();
    }

    // Obtener un vendedor por ID
    public Vendedor getVendedorById(Long id) {
        return vendedorRepository.findById(id).orElseThrow(()->{
        	throw new NotFoundException("No se encontró el vendedor con el ID "+id);
        });
    }

    // Crear un nuevo vendedor con validaciones
    public Vendedor createVendedor(Vendedor vendedor) {
        // Validar que el vendedor no sea nulo
        if (vendedor == null) {
            throw new BadRequestException("El vendedor no puede ser nulo");
        }

        // Validar que el nombre y el email no sean nulos o vacíos
        if (vendedor.getNombre() == null || vendedor.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre del vendedor es obligatorio");
        }

        if (vendedor.getEmail() == null || vendedor.getEmail().trim().isEmpty()) {
            throw new BadRequestException("El email del vendedor es obligatorio");
        }

        // Guardar el vendedor en la base de datos
        return vendedorRepository.save(vendedor);
    }

    // Actualizar un vendedor existente
    public Vendedor updateVendedor(Long id, Vendedor actVendedor) {
        // Buscar el vendedor existente
        Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(()->{
        	throw new NotFoundException("No se encontró el vendedor con el ID "+id);
        });
        
     // Validar que el vendedor no sea nulo
        if (actVendedor == null) {
            throw new BadRequestException("El vendedor no puede ser nulo");
        }

        // Validar que el nombre y el email no sean nulos o vacíos
        if (actVendedor.getNombre() == null || actVendedor.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre del vendedor es obligatorio");
        }

        if (actVendedor.getEmail() == null || actVendedor.getEmail().trim().isEmpty()) {
            throw new BadRequestException("El email del vendedor es obligatorio");
        }

        // Actualizar los campos del vendedor
        vendedor.setNombre(actVendedor.getNombre());
        vendedor.setEmail(actVendedor.getEmail());

        // Guardar el vendedor actualizado
        return vendedorRepository.save(vendedor);
    }

    // Eliminar un vendedor
    public void deleteVendedor(Long id) {
        if (vendedorRepository.existsById(id)) {
            vendedorRepository.deleteById(id);
        } else {
            throw new NotFoundException("No se encontró el vendedor con el ID "+id);
        }
    }
}