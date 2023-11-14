package org.madmotor.apimadmotor.clientes.Controller;

import org.madmotor.apimadmotor.clientes.dto.ClienteCreateRequest;
import org.madmotor.apimadmotor.clientes.dto.ClienteReponse;
import org.madmotor.apimadmotor.clientes.dto.ClienteUpdateRequest;
import org.madmotor.apimadmotor.clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CacheConfig(cacheNames = {"clientes"})
public class ClienteRestController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<ClienteReponse>> getAllClientes(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @Cacheable
    @GetMapping("/{id}")
    public ResponseEntity<ClienteReponse> getClienteByDni(@PathVariable String id){
        return ResponseEntity.ok(clienteService.findByDni(id));
    }

    @CachePut
    @PostMapping
    public ResponseEntity<ClienteReponse> createCliente(@RequestBody ClienteCreateRequest clienteCreateRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.savePost((clienteCreateRequest)));
    }

    @CachePut
    @PutMapping("/{id}")
    public ResponseEntity<ClienteReponse> updateCliente(@PathVariable String id, @RequestBody ClienteUpdateRequest clienteUpdateRequest){
        return ResponseEntity.ok(clienteService.updateByDni(id,clienteUpdateRequest));
    }

    @Cacheable
    @PatchMapping("/{id}")
    public ResponseEntity<ClienteReponse>patchCliente(@PathVariable String id, @RequestBody ClienteUpdateRequest clienteUpdateRequest){
        return ResponseEntity.ok(clienteService.updateByDni(id,clienteUpdateRequest));
    }
    @CacheEvict
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteCliente(@PathVariable String id) {
        clienteService.deleteByDni(id);
        return ResponseEntity.noContent().build();
    }
}
