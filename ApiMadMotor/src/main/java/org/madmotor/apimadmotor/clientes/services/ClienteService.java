package org.madmotor.apimadmotor.clientes.services;

import org.madmotor.apimadmotor.clientes.dto.ClienteCreateRequest;
import org.madmotor.apimadmotor.clientes.dto.ClienteReponse;
import org.madmotor.apimadmotor.clientes.dto.ClienteUpdateRequest;

import java.util.List;

public interface ClienteService {
    ClienteReponse save(ClienteCreateRequest clienteCreateRequest);
    ClienteReponse updateByDni(String dni, ClienteUpdateRequest clienteUpdateRequest);
    ClienteReponse findByDni(String dni);
    List<ClienteReponse> findAll();
    void deleteByDni(String dni);

    ClienteReponse savePost(ClienteCreateRequest clienteCreateRequest);
}
