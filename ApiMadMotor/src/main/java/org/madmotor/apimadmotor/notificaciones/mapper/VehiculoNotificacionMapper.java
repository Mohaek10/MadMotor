package org.madmotor.apimadmotor.notificaciones.mapper;

import org.madmotor.apimadmotor.notificaciones.dto.VehiculoNotificacionDto;
import org.madmotor.apimadmotor.vehiculos.models.Vehiculo;
import org.springframework.stereotype.Component;

@Component
public class VehiculoNotificacionMapper {
    public VehiculoNotificacionDto toVehiculoNotificacionDto(Vehiculo vehiculo) {
        return new VehiculoNotificacionDto(vehiculo.getId(), vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getYear(), vehiculo.getKm(), vehiculo.getPrecio(), vehiculo.getStock(), vehiculo.getImagen(), vehiculo.getDescripcion(), vehiculo.getCreatedAt().toString(), vehiculo.getUpdatedAt().toString(), vehiculo.getCategoria().getName(), vehiculo.getIsDeleted());
    }
}
