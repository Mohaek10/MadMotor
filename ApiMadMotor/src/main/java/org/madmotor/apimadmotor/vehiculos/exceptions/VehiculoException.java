package org.madmotor.apimadmotor.vehiculos.exceptions;

public abstract class VehiculoException extends RuntimeException{
    public VehiculoException(String message) {
        super(message);
    }
}
