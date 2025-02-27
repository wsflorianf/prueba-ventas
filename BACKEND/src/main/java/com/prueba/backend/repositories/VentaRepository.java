package com.prueba.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.backend.models.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {}
