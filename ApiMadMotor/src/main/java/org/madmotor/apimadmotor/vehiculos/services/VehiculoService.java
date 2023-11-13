package org.madmotor.apimadmotor.vehiculos.services;

import org.madmotor.apimadmotor.vehiculos.dto.VehiculoCreateDto;
import org.madmotor.apimadmotor.vehiculos.models.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface VehiculoService {
    Page<Vehiculo> findAll(Optional<String> marca, Optional<String> categoria, Optional<Integer> minYear, Optional<Boolean> isDelete, Optional<Double>kmMax, Optional<Double> precioMax , Optional<Double> stockMin, Pageable pageable);

    Vehiculo findById(String id);

    Vehiculo save(VehiculoCreateDto vehiculoCreateDto);

    Vehiculo update(String id, VehiculoCreateDto vehiculoCreateDto);

    void deleteById(String id);


}
