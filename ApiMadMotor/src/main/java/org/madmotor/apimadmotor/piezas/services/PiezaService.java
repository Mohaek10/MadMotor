package org.madmotor.apimadmotor.piezas.services;

import org.madmotor.apimadmotor.piezas.dto.PiezaCreateDTO;
import org.madmotor.apimadmotor.piezas.dto.PiezaResponseDTO;
import org.madmotor.apimadmotor.piezas.dto.PiezaUpdateDTO;
import org.madmotor.apimadmotor.piezas.models.Pieza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface PiezaService {
    Page<PiezaResponseDTO> findAll(Optional<String> name, Optional<String> description, Optional<Double> price, Optional<Integer> stock, Pageable pageable);

    PiezaResponseDTO findById(UUID id);
    PiezaResponseDTO save(PiezaCreateDTO pieza);
    PiezaResponseDTO update(UUID id, PiezaUpdateDTO pieza);
    void deleteById(UUID id);
    PiezaResponseDTO updateImage(UUID id, MultipartFile image, String url);




    }
