package com.prueba.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.backend.dtos.NuevaVentaDto;
import com.prueba.backend.exceptions.BadRequestException;
import com.prueba.backend.exceptions.NotFoundException;
import com.prueba.backend.models.Producto;
import com.prueba.backend.models.Vendedor;
import com.prueba.backend.models.Venta;
import com.prueba.backend.repositories.ProductoRepository;
import com.prueba.backend.repositories.VendedorRepository;
import com.prueba.backend.repositories.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    
    @Autowired
    private VendedorRepository vendedorRepository;
    
    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todas las ventas
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    // Obtener una venta por ID
    public Venta getVentaById(Long id) {
        return ventaRepository.findById(id).orElseThrow(()->{
        	throw new NotFoundException("No se encontró la venta con el ID "+id);
        });
    }

    // Crear una nueva venta con validaciones
    public Venta createVenta(NuevaVentaDto nueva) {
    	
        // Validar que la venta no sea nula
        if (nueva == null) {
            throw new BadRequestException("La venta no puede ser nula");
        }

        // Validar que el vendedor y el producto no sean nulos
        if (nueva.getVendedorId() == null || nueva.getProductoId() == null) {
            throw new BadRequestException("El vendedor y el producto son obligatorios");
        }
    	
    	//Comprobar vendedor
    	Vendedor vendedor = vendedorRepository.findById(nueva.getVendedorId())
                .orElseThrow(() -> new BadRequestException("Vendedor no encontrado"));

    	//Comprobar producto
    	Producto producto = productoRepository.findById(nueva.getProductoId())
                .orElseThrow(() -> new BadRequestException("Producto no encontrado"));

        // Validar que la cantidad sea mayor que 0
        if (nueva.getCantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor que 0");
        }
        
        // Crear la entidad Venta
        Venta venta = new Venta();
        venta.setVendedor(vendedor);
        venta.setProducto(producto);
        venta.setCantidad(nueva.getCantidad());
        venta.setFecha(nueva.getFecha());


        // Guardar la venta en la base de datos
        return ventaRepository.save(venta);
    }

    // Actualizar una venta existente
    public Venta updateVenta(Long id, NuevaVentaDto actVenta) {
        // Buscar la venta existente
        Venta venta = ventaRepository.findById(id).orElseThrow(()->{
        	throw new NotFoundException("No se encontró la venta con el ID "+id);
        });
        
        // Validar que la venta no sea nula
        if (actVenta == null) {
            throw new BadRequestException("La venta no puede ser nula");
        }

        // Validar que el vendedor y el producto no sean nulos
        if (actVenta.getVendedorId() == null || actVenta.getProductoId() == null) {
            throw new BadRequestException("El vendedor y el producto son obligatorios");
        }
    	
    	//Comprobar vendedor
    	Vendedor vendedor = vendedorRepository.findById(actVenta.getVendedorId())
                .orElseThrow(() -> new BadRequestException("Vendedor no encontrado"));

    	//Comprobar producto
    	Producto producto = productoRepository.findById(actVenta.getProductoId())
                .orElseThrow(() -> new BadRequestException("Producto no encontrado"));

        // Validar que la cantidad sea mayor que 0
        if (actVenta.getCantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor que 0");
        }

        // Actualizar los campos de la venta
        venta.setVendedor(vendedor);
        venta.setProducto(producto);
        venta.setCantidad(actVenta.getCantidad());
        venta.setFecha(actVenta.getFecha());

        // Guardar la venta actualizada
        return ventaRepository.save(venta);
            
        
    }

    // Eliminar una venta
    public void deleteVenta(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
        } else {
            throw new BadRequestException("No se encontró la venta con el ID "+id);
        }
    }
}