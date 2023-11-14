package org.madmotor.apimadmotor.clientes.services;

import org.madmotor.apimadmotor.clientes.Repository.ClienteRepository;
import org.madmotor.apimadmotor.clientes.dto.ClienteCreateRequest;
import org.madmotor.apimadmotor.clientes.dto.ClienteReponse;
import org.madmotor.apimadmotor.clientes.dto.ClienteUpdateRequest;
import org.madmotor.apimadmotor.clientes.exceptions.ClienteFailList;
import org.madmotor.apimadmotor.clientes.exceptions.ClienteFailSave;
import org.madmotor.apimadmotor.clientes.exceptions.ClienteNotFound;
import org.madmotor.apimadmotor.clientes.mappers.ClienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository clienteRepository;
    private final Logger log =  LoggerFactory.getLogger(ClienteServiceImpl.class);
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }


    @Override
    public ClienteReponse save(ClienteCreateRequest clienteCreateRequest) {
    log.info("Guardando a un nuevo cliente");
        var clienteGuardado= clienteRepository.save(clienteMapper.toCliente(clienteCreateRequest));
        return clienteMapper.toClienteReponse(clienteGuardado);
    }

    @Override
    public ClienteReponse updateByDni(String dni, ClienteUpdateRequest clienteUpdateRequest) {
        log.info("Actualizando el cliente con el DNI número: " + dni);

        // Buscar el cliente existente por su DNI
        var clienteActualizar = clienteRepository.findByDniEqualsIgnoreCase(dni)
                .orElseThrow(() -> new ClienteNotFound(dni));

        clienteActualizar.setNombre(clienteUpdateRequest.getNombre());
        clienteActualizar.setApellido(clienteUpdateRequest.getApellido());
        clienteActualizar.setDireccion(clienteUpdateRequest.getDireccion());
        clienteActualizar.setCodigoPostal(clienteUpdateRequest.getCodigoPostal());
        clienteActualizar.setPiezas(clienteUpdateRequest.getPiezas());
        clienteActualizar.setCoches(clienteUpdateRequest.getCoches());

        // Guardar el cliente actualizado
        var clienteActualizado = clienteRepository.save(clienteActualizar);


        return clienteMapper.toClienteReponse(clienteActualizado);
    }


    @Override
    public ClienteReponse findByDni(String dni) {
        log.info("Buscando el cliente con el dni numero : "+dni );
        return clienteMapper.toClienteReponse(clienteRepository.findByDniEqualsIgnoreCase(dni).orElseThrow(()->new ClienteNotFound(dni)));
    }

    @Override
    public List<ClienteReponse> findAll() {
        log.info("Dando un listado de todos los clientes");
        try {
         return clienteRepository.findAll().stream().map(clienteMapper::toClienteReponse).toList();
         } catch (Exception e) {
        log.error("Error al obtener la lista de clientes", e);
        throw new ClienteFailList("Error al obtener la lista de clientes");
    }
    }

    @Override
    public void deleteByDni(String dni) {
        log.info("Eliminando el cliente con el dni numero : " + dni);
        var clienteAElminar = clienteRepository.findByDniEqualsIgnoreCase(dni).orElseThrow(() -> new ClienteNotFound(dni));
        clienteRepository.delete(clienteAElminar);
    }

    @Override
    public ClienteReponse savePost(ClienteCreateRequest clienteCreateRequest) {

        if( clienteRepository.findByDniEqualsIgnoreCase(clienteCreateRequest.getDni()).isEmpty()){
            return this.save(clienteCreateRequest);
        }else{
            throw new ClienteFailSave("Ha habido un error al registrar al cliente");
        }
    }
}
