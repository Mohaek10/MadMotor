package org.madmotor.apimadmotor.clientes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
@Builder
@Schema(description = "Crea un nuevo cliente")
public class ClienteCreateRequest {

    @Length(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    @NotBlank(message = "Necesitamos conocer su nombre")
    @Schema(description = "El nombre del cliente")
   private final String nombre;

    @Schema(description = "El apellido del cliente")
    @Length(min = 3, message = "El apellido debe tener al menos 3 caracteres")
    @NotBlank(message="Necesitamos conocer sus apellidos")
   private final String apellido;

    @Schema(description = "La direccion del cliente")
    @Length(min = 1,message = "La direccion debe tener al menos 3 caracteres")
    @NotBlank(message = "Es necesario conocer su direccion")
   private final String direccion;

    @Schema(description = "El codigo postal del cliente")
    @Min(value = 10000,message = "El codigo postal debe tener un minimo de 5 digitos")
    @NotBlank(message = "Es necesario conocer su codigo postal")
   private final Integer codigoPostal;

    @Schema(description = "El dni del cliente")
    @NotBlank(message = "Es necesario conocer su dni")
   private final String dni;

    @Schema(description = "Si el cliente esta interesado en piezas")
    @NotNull(message = "Es necesario conocer si el cliente está interesado en piezas")
    @Builder.Default
    private final Boolean piezas=true;

    @Schema(description = "Si el cliente esta interesado en coches")
    @NotNull(message = "Es necesario conocer si el cliente está interesado en coches")
    @Builder.Default
    private final Boolean coches=true;
}
