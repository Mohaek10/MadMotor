package org.madmotor.apimadmotor.piezas.mappers;

import org.madmotor.apimadmotor.piezas.dto.PiezaCreateDTO;
import org.madmotor.apimadmotor.piezas.dto.PiezaResponseDTO;
import org.madmotor.apimadmotor.piezas.dto.PiezaUpdateDTO;
import org.madmotor.apimadmotor.piezas.models.Pieza;
import org.springframework.stereotype.Component;

@Component
public class PiezaMapper {
    public Pieza toPieza(PiezaCreateDTO piezaCreateDTO) {
        return Pieza.builder()
                .name(piezaCreateDTO.getName())
                .description(piezaCreateDTO.getDescription())
                .price(piezaCreateDTO.getPrice())
                .stock(piezaCreateDTO.getStock())
                .build();
    }

    public Pieza toPieza(PiezaUpdateDTO piezaUpdateDTO, Pieza pieza) {
        return Pieza.builder()
                .name(piezaUpdateDTO.getName() != null ? piezaUpdateDTO.getName() : pieza.getName())
                .description(piezaUpdateDTO.getDescription() != null ? piezaUpdateDTO.getDescription() : pieza.getDescription())
                .price(piezaUpdateDTO.getPrice() != null ? piezaUpdateDTO.getPrice() : pieza.getPrice())
                .stock(piezaUpdateDTO.getStock() != null ? piezaUpdateDTO.getStock() : pieza.getStock())
                .build();

    }
    public PiezaResponseDTO toPiezaResponse(Pieza pieza) {
        return PiezaResponseDTO.builder()
                .id(pieza.getId())
                .name(pieza.getName())
                .description(pieza.getDescription())
                .price(pieza.getPrice())
                .stock(pieza.getStock())
                .image(pieza.getImage())
                .build();
    }


}
