package org.madmotor.apimadmotor.clientes.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    //Campo inmutable para que el cliente se unico
    @Id
    @GeneratedValue(generator = "uuid2")
    UUID codCliente;
    @NonNull
    @NotBlank(message = "Necesitamos conocer su nombre")
    String nombre;
    @NonNull
    @NotBlank(message="Necesitamos conocer sus apellidos")
    String apellido;
    String direccion;
    Integer codigoPostal;
    @NonNull
    @NotBlank(message = "Es Obligatorio el DNI")
    String dni;
    Boolean piezas;
    Boolean coches;
}
