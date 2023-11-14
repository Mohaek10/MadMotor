package org.madmotor.apimadmotor.clientes.mappers;

import org.madmotor.apimadmotor.clientes.Model.Cliente;
import org.madmotor.apimadmotor.clientes.dto.ClienteCreateRequest;
import org.madmotor.apimadmotor.clientes.dto.ClienteReponse;
import org.madmotor.apimadmotor.clientes.dto.ClienteUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toCliente(ClienteCreateRequest clienteRequest) {
        return Cliente.builder()
              .nombre(clienteRequest.getNombre())
              .apellido(clienteRequest.getApellido())
              .direccion(clienteRequest.getDireccion())
              .codigoPostal(clienteRequest.getCodigoPostal())
                .dni(clienteRequest.getDni())
              .piezas(clienteRequest.getPiezas())
              .coches(clienteRequest.getCoches())
              .build();
    }

    public Cliente toCliente(ClienteUpdateRequest clienteUpdateRequest) {
        return Cliente.builder()
             .nombre(clienteUpdateRequest.getNombre())
             .apellido(clienteUpdateRequest.getApellido())
             .direccion(clienteUpdateRequest.getDireccion())
             .codigoPostal(clienteUpdateRequest.getCodigoPostal())
             .piezas(clienteUpdateRequest.getPiezas())
             .coches(clienteUpdateRequest.getCoches())
             .build();
    }
    public ClienteReponse toClienteReponse(Cliente cliente) {
        return ClienteReponse.builder()
              .nombre(cliente.getNombre())
              .apellido(cliente.getApellido())
              .direccion(cliente.getDireccion())
              .codigoPostal(cliente.getCodigoPostal())
              .piezas(cliente.getPiezas())
              .coches(cliente.getCoches())
                .dni(cliente.getDni())
              .build();
    }
}
