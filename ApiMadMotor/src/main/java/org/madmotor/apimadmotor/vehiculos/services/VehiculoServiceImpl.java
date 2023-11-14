package org.madmotor.apimadmotor.vehiculos.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.madmotor.apimadmotor.vehiculos.dto.VehiculoCreateDto;
import org.madmotor.apimadmotor.vehiculos.mapper.VehiculoMapper;
import org.madmotor.apimadmotor.vehiculos.models.Vehiculo;
import org.madmotor.apimadmotor.vehiculos.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@CacheConfig(cacheNames = {"vehiculos"})
@Slf4j
public class VehiculoServiceImpl implements VehiculoService{
    private final VehiculoRepository vehiculoRepository;
    private final VehiculoMapper vehiculoMapper;
    private final ObjectMapper mapper;

    @Autowired
    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, VehiculoMapper vehiculoMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.vehiculoMapper = vehiculoMapper;
        mapper = new ObjectMapper();
    }
    @Override
    public Page<Vehiculo> findAll(Optional<String> marca, Optional<String> categoria, Optional<Integer> minYear, Optional<Boolean> isDelete, Optional<Double> kmMax, Optional<Double> precioMax, Optional<Double> stockMin, Pageable pageable) {
        return null;
    }

    @Override
    @Cacheable
    public Vehiculo findById(String id) {
        log.info("Buscando por UUID "+id);
        var myID= UUID.fromString(id);
        return vehiculoRepository.findById(myID).orElseThrow(()-> new RuntimeException("No se encontro el vehiculo con el id "+id));

    }

    @CachePut
    @Override
    public Vehiculo save(VehiculoCreateDto vehiculoCreateDto) {
        return null;
    }

    @Override
    public Vehiculo update(String id, VehiculoCreateDto vehiculoCreateDto) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}