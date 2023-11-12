package org.madmotor.apimadmotor.piezas.repositories;

import org.madmotor.apimadmotor.piezas.models.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PiezaRepository extends JpaRepository<Pieza, UUID> {
    
}
