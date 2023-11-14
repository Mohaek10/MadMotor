package org.madmotor.apimadmotor.personal.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data

public class PersonalUpdateDTO {

    @Length(min = 9, max = 9, message = "El DNI debe ser de 8 dígitos y una letra")
    private String dni;

    @Length(min = 3, max = 20, message = "El nombre debe ocupàr entre 3 y 20 caracteres")
    private String nombre;

    @Length(min = 3, max = 50, message = "El/Los apellido(s) debe(n) ocupàr entre 3 y 50 caracteres")
    private String apellidos;

    private LocalDate fechaNacimiento;

    @Length(min = 3, max = 150, message = "La dirección debe de tener entre 3 y 150 caracteres")
    private String direccion;

    @Length(min = 20, max = 20, message = "La cuenta de banco debe de contener 20 caracteres, los dos primeros son ES y los demás dígitos")
    private String iban;

}
