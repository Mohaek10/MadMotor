package org.madmotor.apimadmotor.piezas.repositories;

import org.madmotor.apimadmotor.piezas.models.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PiezaRepository extends JpaRepository<Pieza, UUID> {
    

}
