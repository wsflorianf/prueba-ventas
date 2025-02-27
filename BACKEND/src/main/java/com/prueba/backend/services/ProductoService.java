package com.prueba.backend.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.backend.exceptions.BadRequestException;
import com.prueba.backend.exceptions.NotFoundException;
import com.prueba.backend.models.Producto;
import com.prueba.backend.repositories.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElseThrow(()->{
        	throw new NotFoundException("No se encontró el producto con el ID "+id );
        });
    }

    // Crear un nuevo producto con validaciones
    public Producto createProducto(Producto producto) {
        // Validar que el producto no sea nulo
        if (producto == null) {
            throw new BadRequestException("El producto no puede ser nulo");
        }

        // Validar que el nombre no sea nulo o vacío
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre del producto es obligatorio");
        }

        // Validar que el precio sea mayor que 0
        if (producto.getPrecio().compareTo(BigDecimal.ZERO) > 0) {
            throw new BadRequestException("El precio debe ser mayor que 0");
        }

        // Validar que el stock no sea negativo
        if (producto.getStock() < 0) {
            throw new BadRequestException("El stock no puede ser negativo");
        }

        // Guardar el producto en la base de datos
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto updateProducto(Long id, Producto actProducto) {
        // Buscar el producto existente
        Producto producto = productoRepository.findById(id).orElseThrow(()->{
        	throw new NotFoundException("No se encontró el producto con el ID "+id );
        });
        
        if (actProducto == null) {
            throw new BadRequestException("El producto no puede ser nulo");
        }

        // Validar que el nombre no sea nulo o vacío
        if (actProducto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre del producto es obligatorio");
        }

        // Validar que el precio sea mayor que 0
        if (actProducto.getPrecio().compareTo(BigDecimal.ZERO) > 0) {
            throw new BadRequestException("El precio debe ser mayor que 0");
        }

        // Validar que el stock no sea negativo
        if (actProducto.getStock() < 0) {
            throw new BadRequestException("El stock no puede ser negativo");
        }


        // Actualizar los campos del producto
        producto.setNombre(actProducto.getNombre());
        producto.setPrecio(actProducto.getPrecio());
        producto.setStock(actProducto.getStock());

        // Guardar el producto actualizado
        return productoRepository.save(producto);
    }

    // Eliminar un producto
    public void deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Producto no encontrado con ID: " + id);
        }
    }
}