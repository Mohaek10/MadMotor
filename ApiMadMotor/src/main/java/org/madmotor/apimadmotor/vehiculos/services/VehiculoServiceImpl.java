package org.madmotor.apimadmotor.vehiculos.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.madmotor.apimadmotor.categorias.models.Categoria;
import org.madmotor.apimadmotor.categorias.services.CategoriaService;
import org.madmotor.apimadmotor.notificaciones.configurations.WebSocketConfig;
import org.madmotor.apimadmotor.notificaciones.configurations.WebSocketHandler;
import org.madmotor.apimadmotor.notificaciones.dto.VehiculoNotificacionDto;
import org.madmotor.apimadmotor.notificaciones.mapper.VehiculoNotificacionMapper;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@CacheConfig(cacheNames = {"vehiculos"})
public class VehiculoServiceImpl implements VehiculoService{
    private final VehiculoRepository vehiculoRepository;
    private final CategoriaService categoriaService;
    private final VehiculoMapper vehiculoMapper;
    private final ObjectMapper mapper;
    WebSocketConfig webSocketConfig ;
    VehiculoNotificacionMapper vehiculoNotificacionMapper;
    private WebSocketHandler webSocketService;


    @Autowired
    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository,CategoriaService categoriaService, VehiculoMapper vehiculoMapper, WebSocketConfig webSocketConfig, VehiculoNotificacionMapper notificacioMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.categoriaService = categoriaService;
        this.vehiculoMapper = vehiculoMapper;
        this.webSocketConfig = webSocketConfig;
        webSocketService=webSocketConfig.webSocketVehiHandler();
        mapper=new ObjectMapper();
        this.vehiculoNotificacionMapper=notificacioMapper;
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

