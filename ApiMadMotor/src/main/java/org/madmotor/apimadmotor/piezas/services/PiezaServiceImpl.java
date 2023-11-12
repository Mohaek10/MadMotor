package org.madmotor.apimadmotor.piezas.services;

import lombok.extern.slf4j.Slf4j;
import org.madmotor.apimadmotor.piezas.dto.PiezaCreateDTO;
import org.madmotor.apimadmotor.piezas.dto.PiezaResponseDTO;
import org.madmotor.apimadmotor.piezas.dto.PiezaUpdateDTO;
import org.madmotor.apimadmotor.piezas.repositories.PiezaRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;
@Service
@CacheConfig(cacheNames = "piezas")
@Slf4j
public class PiezaServiceImpl extends PiezaService {
    private final PiezaRepository piezaRepository;
    private final PiezaMapper piezaMapper;


    @Override
    public Page<PiezaResponseDTO> findAll(Optional<UUID> id, Optional<String> name, Optional<String> description, Optional<Double> price, Optional<String> image, Pageable pageable) {

    }

    @Override
    public PiezaResponseDTO findById(UUID id) {
        return null;
    }

    @Override
    public PiezaResponseDTO save(PiezaCreateDTO pieza) {
        return null;
    }

    @Override
    public PiezaResponseDTO update(UUID id, PiezaUpdateDTO pieza) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public PiezaResponseDTO updateImage(UUID id, MultipartFile image, String url) {
        return null;
    }
}
