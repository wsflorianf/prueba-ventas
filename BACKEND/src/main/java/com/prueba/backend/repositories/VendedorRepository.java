package com.prueba.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.backend.models.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {}