package org.madmotor.apimadmotor.clientes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteFailSave extends ClienteException{
    public ClienteFailSave(String message) {
        super(message);
    }
}
