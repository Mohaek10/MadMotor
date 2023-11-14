package org.madmotor.apimadmotor.personal.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalResponseDTO {
    private Long id;
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String iban;


}
