package org.madmotor.apimadmotor.notificaciones.dto;
import java.util.UUID;

public record VehiculoNotificacionDto ( UUID id,
        String marca,
        String modelo,
        Integer year,
        Double km,
        Double precio,
        Integer stock,
        String imagen,
        String descripcion,
        String createdAt,
        String updatedAt,
        String categoria,
        Boolean isDeleted){

}
